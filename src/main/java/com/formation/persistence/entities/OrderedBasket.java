package com.formation.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table (name = "ordered_baskets") @Entity
public class OrderedBasket {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;
	@Column (nullable = false)
	Double quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "basket_id",referencedColumnName = "id", nullable = false)
	BasketType basket;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public BasketType getBasket() {
		return basket;
	}

	public void setBasket(BasketType basket) {
		this.basket = basket;
	}
	
	
}
