package com.formation.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.formation.persistence.entities.Admin;
import com.formation.persistence.entities.Client;
import com.formation.persistence.repository.IAdminRepository;
import com.formation.services.IAdminService;
import com.formation.services.common.implementation.AbstractService;


@Service
public class AdminService extends AbstractService<Admin> implements IAdminService {

	@Autowired
	private IAdminRepository repo;

	@Override
	public JpaRepository<Admin, Long> getRepo() {
		return this.repo;
	}
	
	@Override
	public Admin findByMail(String username) {
		return repo.findByMail(username);
	}	

}
