package com.formation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.clients.ClientFull;
import com.formation.dto.clients.ClientLight;
import com.formation.exceptions.NotAuthorizedException;
import com.formation.persistence.entities.Client;
import com.formation.services.IAuthChecker;
import com.formation.services.IClientService;


@RestController
@RequestMapping(path = "/api/private/client")
public class ClientController {


	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IClientService service;
	
	@Autowired
	private IAuthChecker authChecker;
	
	public ClientController() {
		System.out.println("instanciation du controller de client");
	}
	
	@GetMapping
	public List<ClientLight> findAll(){
		
		if (authChecker.getCurrentAdmin() != null ) {
			return service.findAll()
					.stream()
					.map(c -> mapper.map(c, ClientLight.class))
					.collect(Collectors.toList());
		} else {
			throw new NotAuthorizedException();
		}
		
		
	}
/*
	@GetMapping(path="/{identifiant}")  // version cool de @RequestMapping(path="/{identifiant}", method = RequestMethod.GET) 
	public ClientFull findOne (@PathVariable(name = "identifiant") Long id) {	
		return mapper.map(service.findOne(id), ClientFull.class);

	}
	*/
	@DeleteMapping(path="/{id}")  
	public void delete (@PathVariable Long id) {
		try {
			service.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostMapping() 
	public ClientFull save(@RequestBody ClientFull cl) {
		return mapper.map(service.save(mapper.map(cl, Client.class)), ClientFull.class);	
	}
	
}
