package com.formation.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.Client;
import com.formation.persistence.repository.IClientRepository;
import com.formation.services.IClientService;
import com.formation.services.common.implementation.AbstractService;

public class ClientService extends AbstractService<Client> implements IClientService {

	@Autowired
	private IClientRepository repo;

	@Override
	public JpaRepository<Client, Long> getRepo() {
		return this.repo;
	}
	
	@Override
	public Client findByMail(String username) {
		return repo.findByMail(username);
	}	

}
