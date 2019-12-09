package com.formation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.Place;

public interface IPlaceRepository extends JpaRepository<Place,Long> {
	
	
}
