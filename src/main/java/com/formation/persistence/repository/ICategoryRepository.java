package com.formation.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.persistence.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
	
	@Query(nativeQuery = true,value = "SELECT * FROM categories WHERE label LIKE ?1")
	public List<Category> findByTitle(String label);
	
	
}
