package com.formation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.clients.ClientFull;
import com.formation.dto.clients.ClientLight;
import com.formation.dto.clients.PasswordChangeRequest;
import com.formation.exceptions.NotApplicableException;
import com.formation.exceptions.NotAuthorizedException;
import com.formation.persistence.entities.Client;
import com.formation.services.IAuthChecker;
import com.formation.services.IClientService;


/**
 * @author Aelion
 *
 */

@RestController
@RequestMapping(path = "/api/private/client")
public class ClientController {


	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IClientService service;
	
	@Autowired
	private IAuthChecker authChecker;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	
	/**
	 * @return the list of all ClientLight in the database
	 * this action is only allowed to the administrator and throw an exception if launched by any other user
	 */
	@GetMapping
	public List<ClientLight> findAll(){		
		if (authChecker.getCurrentAdmin() != null ) {
			return service.findAll()
					.stream()
					.map(c -> mapper.map(c, ClientLight.class))
					.collect(Collectors.toList());
		} 
		throw new NotAuthorizedException();		
	}
	

	
	/**
	 * @param id
	 * @return the ClientFull that has this id in the database
	 * this action is only allowed to the administrator
	 */
	@GetMapping(path="/{identifiant}")  
	public ClientFull findOne (@PathVariable(name = "identifiant") Long id) {			
		if (authChecker.getCurrentAdmin() != null ) {
			return mapper.map(service.findOne(id), ClientFull.class);
		}
		throw new NotAuthorizedException();	
	}
	
	/**
	 * @return the ClientFull object matching the client sending the request
	 * this action is only allowed an authenticated client
	 */
	@GetMapping(path="/me")
	public ClientFull findOne() {			
	
		if (authChecker.getCurrentClient() != null) {
			return mapper.map(service.findOne(authChecker.getCurrentClient().getId()), ClientFull.class);
		}
		else if (authChecker.getCurrentAdmin() != null ) {
			throw new NotApplicableException("You must specify an ID");	
		}
		throw new NotAuthorizedException();	
	}
	
	
	// TODO
	// mettre des gardes fous, il faut vérifier un tas de trucs avant de pouvoir supprimer un client de la BD pour éviter les fausses manips
	
	/**
	 * @param id
	 * delete the client with the ID id
	 * 	this action is only allowed to the administrator
	 */
	@DeleteMapping(path="/{id}")  
	public void delete (@PathVariable Long id) {
		if (authChecker.getCurrentAdmin() != null ) { // je suis l'admin, j'ai le droit
		
			try {
				service.deleteById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new NotAuthorizedException();	
		}
	}
	
	
	/**
	 * delete the client who is sending the request
	 * 	this action is only allowed to an authenticated client
	 */
	@DeleteMapping  
	public void delete () {
		if  (authChecker.getCurrentClient() != null) {  
			try {
				service.deleteById(authChecker.getCurrentClient().getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (authChecker.getCurrentAdmin() != null ) { 
			throw new NotApplicableException();
		}else{
			throw new NotAuthorizedException();	
		}
	}
	
	
	/**
	 * @param client to save
	 * @return the client effectively saved
	 * save or update a client in the database
	 * this action can only be done by the administrator
	 */
	@PostMapping
	public ClientFull save(@RequestBody ClientFull cl) {
		
		if (authChecker.getCurrentAdmin() == null) { //on est aps admin on a pas le droit
			throw new NotAuthorizedException();
		}
	
		Client temp = new Client();	
		if (cl.getId() != null) temp = service.findOne(cl.getId());
			
		/* creation and update */
		
		/* id */
		temp.setId(cl.getId());	
		/* first name */
		temp.setFirstName(cl.getFirstName() != null ? cl.getFirstName() : "Anonyme");		
		/* name */
		if (cl.getName() != null) temp.setName(cl.getName());		
		/* email */
		if (cl.getEmail() != null) temp.setEmail(cl.getEmail());		
		/* phone */
		if (cl.getPhone() != null) temp.setPhone(cl.getPhone());		
		/* birthday */
		if (cl.getBirthDate() != null) temp.setBirthDate(cl.getBirthDate());
				
		/* creation only */
		if (cl.getId() == null) {				
			/* password */
			/* TODO */
			/* faut en générer un aléatoirement, ça c'est degueu faudra JAMAIS le garder */				
			temp.setPassword(encoder.encode("salut"));
		}
		return mapper.map(service.save(temp), ClientFull.class);		
	}

	

	/**
	 * @param client to update
	 * @return the client effectively saved
	 * change informations of a client profile
	 * this action can only be done by an authenticated client
	 */
	@PostMapping(path="/me")
	public ClientFull update(@RequestBody ClientFull cl) { 
		
		if (authChecker.getCurrentClient() == null) { // on est pas un client, ça n'a pas de sens
			throw new NotApplicableException();
		}
		
		Client temp = service.findOne(authChecker.getCurrentClient().getId());		
		
		/* first name */
		if (cl.getFirstName() != null) temp.setFirstName(cl.getFirstName());		
		/* name */
		if (cl.getName() != null) temp.setName(cl.getName());		
		/* email */
		if (cl.getEmail() != null) temp.setEmail(cl.getEmail());		
		/* phone */
		if (cl.getPhone() != null) temp.setPhone(cl.getPhone());		
		/* birthday */
		if (cl.getBirthDate() != null) temp.setBirthDate(cl.getBirthDate());
	
		return mapper.map(service.save(temp), ClientFull.class);		
		
	}
	
	
	@PostMapping(path="/me/password")// c'est quoi un bon path ?
	@ResponseStatus(code = HttpStatus.OK)
	public void updatePassword(@RequestBody PasswordChangeRequest req) { 
		
		Client me = authChecker.getCurrentClient();
		
		if ( me == null) { // on est pas un client, ça n'a pas de sens
			throw new NotApplicableException();
		}
		
		if ( ! encoder.matches(req.getOldOne(), me.getPassword()) ) {
			throw new NotAuthorizedException();
		}

		me.setPassword(encoder.encode(req.getNewOne()));
		
		service.save(me);		
	}



	
}
