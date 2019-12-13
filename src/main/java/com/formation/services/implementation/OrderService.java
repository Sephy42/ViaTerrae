package com.formation.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.exceptions.NotAuthorizedException;
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

	/**
	 *Methode de decrementation de la quantite de paniers disponibles par type a la validation de la commande. 
	 */
	@Override
	public Order save(Order t) {
		Order orderSaved = super.save(t);
		
		
		// TODO update available quantities
		orderSaved.getListBaskets().stream().forEach(p -> {
			p.getBasket().setQuantityAvailable(p.getBasket().getQuantityAvailable() - p.getQuantity().intValue());
			
			if (p.getBasket().getQuantityAvailable() < 0) throw new NotAuthorizedException("Il n'y a pas assez de panier(s) " + p.getBasket().getLabel() + "!!!!");
		});
		//
		
		
		return orderSaved;
	}
	

	
	
	
}
