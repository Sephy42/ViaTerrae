package com.formation.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.persistence.entities.Place;
import com.formation.persistence.repository.IPlaceRepository;
import com.formation.services.IPlaceService;
import com.formation.services.common.implementation.AbstractService;


@Service
@Transactional
public class PlaceService extends AbstractService< Place> implements IPlaceService {
	
	@Autowired
	private IPlaceRepository repo;
	
	@Override
	public JpaRepository <Place, Long> getRepo() {
		return repo; 
	}

}
