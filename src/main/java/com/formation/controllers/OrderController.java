package com.formation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.order.OrderFull;
import com.formation.exceptions.NotAuthorizedException;
import com.formation.persistence.entities.Client;
import com.formation.persistence.entities.Order;
import com.formation.services.IAuthChecker;
import com.formation.services.IOrderService;

@RestController 
@RequestMapping (path = "api/private/order")
public class OrderController {
	
	@Autowired
	private IAuthChecker authChecker;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IOrderService servOrder;
	
	@GetMapping (path = "/{id}")
	public OrderFull findOne (@PathVariable Long id) {
		
		Client me = authChecker.getCurrentClient();
		
		Order order = servOrder.findOne(id);
		if (order.getClient().equals(me))
			return mapper.map(order, OrderFull.class);
		
		throw new NotAuthorizedException("C'est pas toi !");
		
	}
	
	@GetMapping (path = "/list")
	public List<OrderFull> findAll() {
		Client me = authChecker.getCurrentClient();
		
		System.out.println("Called by " + me.getName());
		
		return servOrder.findAllById(me.getId())
				.stream()
				.map(u -> mapper.map(u, OrderFull.class))
				.collect(Collectors.toList());
	}
}
