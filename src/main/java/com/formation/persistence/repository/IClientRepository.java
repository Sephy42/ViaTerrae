package com.formation.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.persistence.entities.Client;

public interface IClientRepository extends JpaRepository<Client,Long> {
	
	@Query(nativeQuery = true,value = "SELECT * FROM clients WHERE name LIKE ?1")
	public List<Client> findByName(String name);
	

}
