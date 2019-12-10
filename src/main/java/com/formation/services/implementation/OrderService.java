package com.formation.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.Order;
import com.formation.persistence.repository.IOrderRepository;
import com.formation.services.IOrderService;
import com.formation.services.common.implementation.AbstractService;

public class OrderService extends AbstractService<Order> implements IOrderService {

	@Autowired
	private IOrderRepository repo;

	@Override
	public JpaRepository<Order, Long> getRepo() {
		return this.repo;
	}
	

}
