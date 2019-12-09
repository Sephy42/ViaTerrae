package com.formation.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.persistence.entities.BasketType;

public interface IBasketTypeRepository extends JpaRepository<BasketType,Long> {
	
	@Query(nativeQuery = true,value = "SELECT * FROM basket_types WHERE id LIKE ?1")
	public Optional<BasketType> findById(Long id);
}
