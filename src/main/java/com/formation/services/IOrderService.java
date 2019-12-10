package com.formation.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.formation.persistence.entities.Order;
import com.formation.services.common.IServiceActions;

@Transactional
public interface IOrderService extends IServiceActions<Order> {
	
	public List<Order> findAllById (Long id);

}
