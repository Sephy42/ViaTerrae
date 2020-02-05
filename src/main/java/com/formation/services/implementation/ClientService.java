package com.formation.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.exceptions.ViaTerraeException;
import com.formation.persistence.entities.Client;
import com.formation.persistence.repository.IClientRepository;
import com.formation.services.IClientService;
import com.formation.services.common.implementation.AbstractService;

@Service
@Transactional
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

	@Override
	public Client save(Client t) {
		
		Client other = findByMail(t.getEmail());
		if (other != null ) throw new ViaTerraeException(ViaTerraeException.RG_MAIL_NOT_UNIQUE);
		return super.save(t);
		
	}
}
