package com.formation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.PickUpDate;

public interface IPickUpDateRepository extends JpaRepository<PickUpDate,Long> {
	
}
