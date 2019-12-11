package com.formation.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.persistence.entities.PickUpDate;
import com.formation.persistence.repository.IPickUpDateRepository;
import com.formation.services.IPickUpDateService;
import com.formation.services.common.implementation.AbstractService;

@Service
@Transactional
public class PickUpDateService extends AbstractService<PickUpDate> implements IPickUpDateService {
	
	@Autowired
	private IPickUpDateRepository repo;
	
	@Override
	public JpaRepository <PickUpDate, Long> getRepo() {
		return repo; 
	}

}
