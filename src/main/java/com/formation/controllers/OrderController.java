package com.formation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.order.OrderFull;
import com.formation.dto.order.OrderLight;
import com.formation.dto.product.ProductFull;
import com.formation.exceptions.NotAuthorizedException;
import com.formation.persistence.entities.Admin;
import com.formation.persistence.entities.BasketedProduct;
import com.formation.persistence.entities.Client;
import com.formation.persistence.entities.Order;
import com.formation.persistence.entities.OrderedBasket;
import com.formation.persistence.entities.Product;
import com.formation.services.IAuthChecker;
import com.formation.services.IBasketTypeService;
import com.formation.services.IClientService;
import com.formation.services.IOrderService;
import com.formation.services.IPlaceService;

@RestController 
@RequestMapping (path = "api/private/order")
public class OrderController {
	
	@Autowired
	private IAuthChecker authChecker;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IOrderService servOrder;
	
	@Autowired
	private IBasketTypeService servBasket;
	
	@Autowired
	private IClientService servClient;
	
	@Autowired
	private IPlaceService servPlace;
	
	
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
	
	
	@PostMapping
	public OrderFull save (@RequestBody OrderLight order) {
		
		//Authentification
		Client client = authChecker.getCurrentClient();
		
		if (client != null && !(client.getId().equals(order.getClient()))) {
			throw new NotAuthorizedException("Non autorisé !");
		}
		
		Order result = new Order ();
		// add the id and date to the order.
		result.setId(order.getId());
		result.setOrderDate(order.getOrderDate());
		result.setPickupDate(order.getPickupDate());
		// add cleanly the set of baskets in the order
		result.getListBaskets().addAll(
			order.getListBaskets().stream()
				.map(orderedBasket -> {
					OrderedBasket opresult = new OrderedBasket ();
					opresult.setId(orderedBasket.getId());
					opresult.setQuantity(orderedBasket.getQuantity());
					opresult.setBasket(servBasket.findOne(orderedBasket.getBasket()));
					return opresult;
				})
				.collect(Collectors.toSet())
		);
		System.out.println(result);
		// add cleanly the client in the order
		result.setClient(servClient.findOne(order.getClient()));
		// add cleanly the place in the order
		result.setPlace(servPlace.findOne(order.getPlace()));
		
		return mapper.map(servOrder.save(result),OrderFull.class);
	}
}
