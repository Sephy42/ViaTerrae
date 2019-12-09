package com.formation.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.persistence.entities.Order;

public interface IOrderRepository extends JpaRepository<Order,Long> {
	
	@Query(nativeQuery = true,value = "SELECT * FROM orders WHERE client_id LIKE ?1")
	public List<Order> findByClient(Long id);
	
	
}