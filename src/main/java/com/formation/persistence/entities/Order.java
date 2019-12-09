package com.formation.persistence.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table (name = "orders") @Entity
public class Order {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;
	@Column (name = "order_date",nullable = false)
	Date orderDate;
	@Column (name = "pickup_date",nullable = false)
	Date pickupDate;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "commande_id",referencedColumnName = "id", nullable = false)
	Set<OrderedBasket> listBaskets;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
	Client client;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "place_id", referencedColumnName = "id", nullable = false)
	Place place;

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

	public Set<OrderedBasket> getListBaskets() {
		return listBaskets;
	}

	public void setListBaskets(Set<OrderedBasket> listBaskets) {
		this.listBaskets = listBaskets;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
	
	
}
