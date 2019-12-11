package com.formation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.BasketedProduct;

public interface IBasktedProductRepository extends JpaRepository<BasketedProduct, Long> {
	
	

}
