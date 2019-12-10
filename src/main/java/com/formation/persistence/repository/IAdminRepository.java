package com.formation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.persistence.entities.Admin;

public interface IAdminRepository extends JpaRepository<Admin,Long> {
	
	
	@Query(nativeQuery = true,value = "SELECT * FROM admins WHERE email = ?1")
	Admin findByMail(String username);
	

}
