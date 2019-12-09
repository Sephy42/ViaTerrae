package com.formation.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.persistence.entities.Product;

public interface IProductRepository extends JpaRepository<Product,Long> {
	
	@Query(nativeQuery = true,value = "SELECT * FROM products WHERE label LIKE ?1")
	public List<Product> findByLabel(String label);
	
	
}
