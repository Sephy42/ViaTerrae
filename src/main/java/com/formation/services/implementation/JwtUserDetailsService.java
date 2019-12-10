package com.formation.services.implementation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.formation.persistence.entities.Client;
import com.formation.services.IClientService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private IClientService clientService;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Client u = clientService.findByMail(username);
		
		if (u != null) {
			return new User(u.getEmail(), u.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}