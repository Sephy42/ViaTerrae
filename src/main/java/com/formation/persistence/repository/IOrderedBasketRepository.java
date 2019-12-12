package com.formation.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.persistence.entities.OrderedBasket;

public interface IOrderedBasketRepository extends JpaRepository<OrderedBasket, Long> {
	@Query (nativeQuery = true, value= "SELECT * FROM ordered_baskets WHERE basket_id = ?1")
	public List<OrderedBasket> findAllByBasketTypeId(Long id);
}
