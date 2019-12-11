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
import com.formation.persistence.entities.Admin;
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
	
	
	/**
	 * @param id client
	 * @param Authentification (isClient or isAdmin or notFound)
	 * @return all orders from client selected by his id
	 * @throws Not Authorized if auth failed
	 * @throws Not found if client is not found
	 */
	@GetMapping (path = "/{id}")
	public OrderFull findOne (@PathVariable Long id) {
		
		Client me = authChecker.getCurrentClient();
		
		Order order = servOrder.findOne(id);
		if (order.getClient().equals(me)) 
		{
			return mapper.map(order, OrderFull.class);
		} else {
			if (authChecker.getCurrentAdmin() != null) {
				return mapper.map(servOrder.findOne(id), OrderFull.class);
			} else {
				throw new NotAuthorizedException("Non autorisé !");
			}
		}

	}
	
	
	
	/**
	 * @param Authentification (isClient ou isAdmin ou notFound)
	 * @return all the commands found in database accessible for token
	 * @throws Not Authorized if auth failed
	 * @throws Not found if client is not found
	 */
	@GetMapping (path = "/list")
	public List<OrderFull> findAll() {
		
		Client me = authChecker.getCurrentClient();
		if (me != null) 
		{
			return servOrder.findAllById(me.getId())
					.stream()
					.map(u -> mapper.map(u, OrderFull.class))
					.collect(Collectors.toList());
		} else {
			if (authChecker.getCurrentAdmin() != null) {
				return servOrder.findAll()
						.stream()
						.map(u -> mapper.map(u, OrderFull.class))
						.collect(Collectors.toList());
			} else {
				throw new NotAuthorizedException("Non autorisé !");
			}
		}

	}
}
