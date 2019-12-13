package com.formation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.weaver.ast.Not;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.order.OrderFull;
import com.formation.dto.order.OrderLight;
import com.formation.exceptions.NotAuthorizedException;

import com.formation.persistence.entities.Admin;
import com.formation.persistence.entities.Client;
import com.formation.persistence.entities.Order;
import com.formation.persistence.entities.OrderedBasket;
import com.formation.services.IAuthChecker;
import com.formation.services.IBasketTypeService;
import com.formation.services.IClientService;
import com.formation.services.IOrderService;
import com.formation.services.IPickUpDateService;
import com.formation.services.IPlaceService;
import com.formation.services.verification.IVerificationService;


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
	
	@Autowired
	private IPickUpDateService servPickUp;
	
	@Autowired
	private IVerificationService verifOrder;
	
	
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
		
		//Auth validation
		Client client = authChecker.getCurrentClient();
		if (client != null && !(client.getId().equals(order.getClient()))) {
			throw new NotAuthorizedException("Non autorisé !");
		}
		
		Order result = new Order ();
		// add the id and date to the order.
		result.setId(order.getId());
		result.setOrderDate(order.getOrderDate());
		result.setPickupDate(order.getPickupDate());
		
		//Validating create case
		if (order.getId() == null && verifOrder.isOrderCreateable(result)) {
			throw new NotAuthorizedException("Il est trop tard pour créer une commande !");
		}
		
		// Validating save case
		if (order.getId() != null && verifOrder.isOrderSaveable(result)) {
			throw new NotAuthorizedException("Il est trop tard pour sauvegarder une commande !");
		}
		
		// add cleanly the pickup interval in the order
		result.setInterval(servPickUp.findOne(order.getInterval()));
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
	
	
	@DeleteMapping (path = "/{id}")
	public boolean deleteById(@PathVariable Long id) {
		//if (authChecker.getCurrentClient() == null && authChecker.getCurrentAdmin() == null) throw new NotAuthorizedException("Vous n'avez pas les droits pour cette action");
		Client me = authChecker.getCurrentClient();
		Admin admin = authChecker.getCurrentAdmin();
			Order order = servOrder.findOne(id);
			if (order.getClient().equals(me) || admin != null)
			{
				if (verifOrder.isOrderSaveable(order))  throw new NotAuthorizedException("Il est trop tard pour annuler votre commande.");
				return servOrder.deleteById(id);
				}
					else {
						throw new NotAuthorizedException("Vous n'avez pas les droits pour cette action");
			}
	}
	
}
