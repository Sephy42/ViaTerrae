package com.formation.controllers;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
				throw new NotAuthorizedException("Non autoris√© !");
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
				throw new NotAuthorizedException("Non autoris√© !");
			}
		}

	}
	
	
	@DeleteMapping (path = "/{id}")
	public boolean deleteById(@PathVariable Long id) {
				
		if (authChecker.getCurrentClient() == null && authChecker.getCurrentAdmin() == null) throw new NotAuthorizedException("Vous n'avez pas les droits pour cette action");
		
	
		Order currentOrder = servOrder.findOne(id); 
		OrderFull currentOrderF = mapper.map(currentOrder, OrderFull.class);
		Date pickupDate = currentOrderF.getPickupDate();
	
		Calendar cal = Calendar.getInstance(Locale.FRANCE); //crÈer un calendrier avec la logique et la gÈographie franÁaise
		cal.setTime(pickupDate); //on se positionne sur une date prÈcise
		cal.add(Calendar.DAY_OF_YEAR, -1); //on se dÈcalle de -1 jour
		
		if (System.currentTimeMillis() > cal.getTimeInMillis())  throw new NotAuthorizedException("Il est trop tard pour annuler votre commande.");
		//System.currentTimeMillis() : rÈcupËre l'instant prÈsent en miliseconde calculÈ depuis un instant donnÈ sans notion de gÈographie.
		//cal.getTimeInMillis() : convertit cal en temps de miliseconde calculÈ depuis un instant donnÈ sans notion de gÈographie.
		// t1 > t2 : compare deux dates entre elles. 
		
		return servOrder.deleteById(id);
		
	}
	
	
	
	
}
