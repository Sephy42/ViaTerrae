package com.formation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.clients.PasswordChangeRequest;
import com.formation.exceptions.NotAuthorizedException;
import com.formation.persistence.entities.Admin;
import com.formation.services.IAdminService;
import com.formation.services.IAuthChecker;


@RestController
@RequestMapping(path = "/api/private/god")
public class AdminController {

	@Autowired
	private IAdminService service;
	
	@Autowired
	private IAuthChecker authChecker;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@PostMapping(path="/password")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateAdminPassword(@RequestBody PasswordChangeRequest req) { 
		
		Admin me = authChecker.getCurrentAdmin();
		
		if ( me == null) { // on est pas un admin on a pas le droit
			throw new NotAuthorizedException();
		}
		
		if ( ! encoder.matches(req.getOldOne(), me.getPassword()) ) {
			throw new NotAuthorizedException();
		}

		me.setPassword(encoder.encode(req.getNewOne()));
		
		service.save(me);		
	}
}
