package com.formation.services;

import java.util.List;

import com.formation.persistence.entities.Order;
import com.formation.services.common.IServiceActions;

public interface IOrderService extends IServiceActions<Order> {
	
	public List<Order> findAllById (Long id);

}
