package com.formation.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.persistence.entities.BasketedProduct;

public interface IBasketedProductRepository extends JpaRepository<BasketedProduct, Long> {

	@Query (nativeQuery = true, value= "SELECT * FROM basketed_products WHERE product_id = ?1")
	List<BasketedProduct> findAllByProduct(Long id);

}
