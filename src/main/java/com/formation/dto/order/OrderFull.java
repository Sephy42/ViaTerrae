package com.formation.dto.order;

import java.util.Date;

public class OrderFull {
	
	private Long id;
	private Date orderDate;
	private Date pickupDate;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}
	
//	private Set<OrderedBasket> listBaskets;
	
//	private ClientFull client;
	
//	private PlaceFull place;
	
	
}
