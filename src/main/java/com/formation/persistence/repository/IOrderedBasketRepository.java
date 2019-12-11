package com.formation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.OrderedBasket;

public interface IOrderedBasketRepository extends JpaRepository<OrderedBasket, Long> {

}
