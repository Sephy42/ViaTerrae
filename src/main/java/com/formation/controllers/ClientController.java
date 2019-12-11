package com.formation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.clients.ClientFull;
import com.formation.dto.clients.ClientLight;
import com.formation.dto.clients.ClientToSave;
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
	 * this action is only allowed to the administrator or an identified client if he is checking himself
	 */
	@GetMapping(path="/{identifiant}")  
	public ClientFull findOne (@PathVariable(name = "identifiant") Long id) {			
		if (authChecker.getCurrentAdmin() != null ) {
			return mapper.map(service.findOne(id), ClientFull.class);
		}
		else if  ((authChecker.getCurrentClient() != null)  && (authChecker.getCurrentClient().getId().equals(id)) ) {
			return mapper.map(service.findOne(id), ClientFull.class);
		}
		throw new NotAuthorizedException();	
	}
	
	
	
	@DeleteMapping(path="/{id}")  
	public void delete (@PathVariable Long id) {
		try {
			service.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param cl
	 * @return the client effectively saved
	 * this action can only be done by the administrator
	 */
	@PostMapping
	public ClientToSave save(@RequestBody ClientToSave cl) {
		if (authChecker.getCurrentAdmin() != null ) {
			cl.setPassword(encoder.encode(cl.getPassword()));
			if (cl.getFirstName() == null) {
				cl.setFirstName("Anonyme");
			}
			return mapper.map(service.save(mapper.map(cl, Client.class)), ClientToSave.class);	
		}
		throw new NotAuthorizedException();	
	}

	
}
