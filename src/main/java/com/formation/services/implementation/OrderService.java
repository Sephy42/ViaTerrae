package com.formation.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.persistence.entities.Order;
import com.formation.persistence.repository.IOrderRepository;
import com.formation.services.IOrderService;
import com.formation.services.common.implementation.AbstractService;

@Service
@Transactional
public class OrderService extends AbstractService<Order> implements IOrderService {

	@Autowired
	private IOrderRepository repo;

	@Override
	public JpaRepository<Order, Long> getRepo() {
		return this.repo;
	}

	@Override
	public List<Order> findAllById(Long id) {
		// TODO Auto-generated method stub
		return repo.findByClient(id);
	}
	

}
