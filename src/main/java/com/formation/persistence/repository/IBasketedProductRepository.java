package com.formation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.BasketedProduct;

public interface IBasketedProductRepository extends JpaRepository<BasketedProduct, Long> {
	


}
