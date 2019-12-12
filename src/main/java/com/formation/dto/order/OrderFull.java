package com.formation.dto.order;

import java.util.Date;
import java.util.List;

import com.formation.dto.clients.ClientLight;
import com.formation.dto.pickUpDate.PickUpDateFull;
import com.formation.dto.place.PlaceLight;

public class OrderFull {
	
	Long id;
	Date orderDate;
	PickUpDateFull interval;
	Date pickupDate;
	List<OrderedBasketForOrderFull> listBaskets;
	ClientLight client;
	PlaceLight place;
	
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
	public List<OrderedBasketForOrderFull> getListBaskets() {
		return listBaskets;
	}
	public void setListBaskets(List<OrderedBasketForOrderFull> listBaskets) {
		this.listBaskets = listBaskets;
	}
	public ClientLight getClient() {
		return client;
	}
	public void setClient(ClientLight client) {
		this.client = client;
	}
	public PlaceLight getPlace() {
		return place;
	}
	public void setPlace(PlaceLight place) {
		this.place = place;
	}
	public PickUpDateFull getInterval() {
		return interval;
	}
	public void setInterval(PickUpDateFull interval) {
		this.interval = interval;
	}
	
	
}
