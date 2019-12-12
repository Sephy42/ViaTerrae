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

import com.formation.dto.picture.PictureFull;
import com.formation.exceptions.NotAuthorizedException;
import com.formation.persistence.entities.Picture;
import com.formation.services.IAuthChecker;
import com.formation.services.IPictureService;

@RestController
@RequestMapping(path = "/api/private/pictures")
public class PictureController {
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	IPictureService service;
	
	@Autowired
	private IAuthChecker authchecker; 
	
	@GetMapping 
	public List<PictureFull> findAll() {
		if (authchecker.getCurrentAdmin() == null) throw new NotAuthorizedException("Vous n'avez pas les droits pour cette action");
		return service.findAll()
				.stream()
				.map(pi -> mapper.map(pi, PictureFull.class))
				.collect(Collectors.toList());				
	}
	
	@GetMapping (path = "/{id}")
	public PictureFull findOne(@PathVariable(name = "id") Long id) {
		if (authchecker.getCurrentAdmin() == null) throw new NotAuthorizedException("Vous n'avez pas les droits pour cette action");
		return mapper.map(service.findOne(id), PictureFull.class);		
	}
	
	@DeleteMapping(path = "/{id}")
	public boolean deleteById(@PathVariable(name = "id") Long id) {
		if (authchecker.getCurrentAdmin() == null) throw new NotAuthorizedException("Vous n'avez pas les droits pour cette action");
		return service.deleteById(id);
	}
	
	@PostMapping
	public PictureFull save(@RequestBody PictureFull pictureF) {
		if (authchecker.getCurrentAdmin() == null) throw new NotAuthorizedException("Vous n'avez pas les droits pour cette action");
		
		Picture picture = service.save(mapper.map(pictureF, Picture.class));
		PictureFull pictureFSauve = mapper.map(picture, PictureFull.class);
		return pictureFSauve; 
	}

}
