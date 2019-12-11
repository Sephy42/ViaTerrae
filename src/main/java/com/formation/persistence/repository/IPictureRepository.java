package com.formation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.Picture;

public interface IPictureRepository extends JpaRepository<Picture,Long> {
	
}
