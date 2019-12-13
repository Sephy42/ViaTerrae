package com.formation.dto.order;

import java.util.Date;
import java.util.List;

public class OrderLight {
	
	Long id;
	Date orderDate;
	Long interval;
	Date pickupDate;
	List<OrderedBasketForOrderLight> listBaskets;
	Long client;
	Long place;
	
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
	public List<OrderedBasketForOrderLight> getListBaskets() {
		return listBaskets;
	}
	public void setListBaskets(List<OrderedBasketForOrderLight> listBaskets) {
		this.listBaskets = listBaskets;
	}
	public Long getPlace() {
		return place;
	}
	public void setPlace(Long place) {
		this.place = place;
	}
	public Long getClient() {
		return client;
	}
	public void setClient(Long client) {
		this.client = client;
	}
	public Long getInterval() {
		return interval;
	}
	public void setInterval(Long interval) {
		this.interval = interval;
	}
	
	
	
}
