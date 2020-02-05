package com.formation.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.formation.persistence.entities.Admin;
import com.formation.persistence.entities.Client;
import com.formation.services.IAdminService;
import com.formation.services.IAuthChecker;
import com.formation.services.IClientService;

@Component
public class AuthChecker implements IAuthChecker {

	@Autowired
	private IClientService clientService;
	
	@Autowired
	private IAdminService adminService;
	
	@Override
	public Client getCurrentClient() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails ud = (UserDetails)principal;
			return clientService.findByMail(ud.getUsername());
		} else {
			return null;
		}
	}
	
	@Override
	public Admin getCurrentAdmin() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails ud = (UserDetails)principal;
			return adminService.findByMail(ud.getUsername());
		} else {
			return null;
		}
	}
}
