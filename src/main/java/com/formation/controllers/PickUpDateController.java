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

import com.formation.dto.pickUpDate.PickUpDateFull;
import com.formation.dto.pickUpDate.PickUpDateToSave;
import com.formation.exceptions.NotAuthorizedException;
import com.formation.persistence.entities.PickUpDate;
import com.formation.services.IAuthChecker;
import com.formation.services.IPickUpDateService;

@RestController
@RequestMapping(path = "/api/private/pick")
public class PickUpDateController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	IPickUpDateService service;
	
	@Autowired
	private IAuthChecker authChecker;

	@GetMapping 
	public List<PickUpDateFull> findAll(){		
		if (authChecker.getCurrentAdmin() != null ) {
			return service.findAll()
					.stream()
					.map(c -> mapper.map(c, PickUpDateFull.class))
					.collect(Collectors.toList());
		} 
		throw new NotAuthorizedException();		
			}
	
	@GetMapping(path="/{identifiant}")  
	public PickUpDateFull findOne (@PathVariable(name = "identifiant") Long id) {			
		if (authChecker.getCurrentAdmin() != null ) {
			return mapper.map(service.findOne(id), PickUpDateFull.class);
		}
		
		throw new NotAuthorizedException();	
	}
	
	@DeleteMapping (path = "/{identifiant}")
	public PickUpDateFull deleteById(@PathVariable(name = "identifiant") Long id) {
		if (authChecker.getCurrentAdmin() != null ) {
		return  mapper.map(service.deleteById(id), PickUpDateFull.class);
		}
		
		throw new NotAuthorizedException();
   }
	
	@PostMapping
	public PickUpDateToSave save(@RequestBody PickUpDate pickud) {
		if (authChecker.getCurrentAdmin() != null ) {
			return mapper.map(service.save(mapper.map(pickud, PickUpDate.class)), PickUpDateToSave.class);	
		}
		throw new NotAuthorizedException();	
	}
		
	
}