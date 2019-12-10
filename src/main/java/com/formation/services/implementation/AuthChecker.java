package com.formation.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.formation.exceptions.NotAuthorizedException;
import com.formation.persistence.entities.Client;
import com.formation.services.IAuthChecker;
import com.formation.services.IClientService;

@Component
public class AuthChecker implements IAuthChecker {

	@Autowired
	private IClientService clientService;
	
	@Override
	public Client getCurrentClient() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails ud = (UserDetails)principal;
			return clientService.findByMail(ud.getUsername());
		} else {
			throw new NotAuthorizedException("NO CURRENT USER");
		}
	}
}
