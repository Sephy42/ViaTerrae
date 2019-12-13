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
	Long quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "basket_id",referencedColumnName = "id", nullable = false)
	BasketType basket;
	
	Double costOrdered;
	
	public OrderedBasket() {
	}
	
	public OrderedBasket(BasketType basket) {
		setBasket(basket);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BasketType getBasket() {
		return basket;
	}

	public void setBasket(BasketType basket) {
		this.basket = basket;
		setCostOrdered(basket.getCost());
	}
	
	public Double getCostOrdered() {
		return costOrdered;
	}
	public void setCostOrdered(Double costOrdered) {
		this.costOrdered = costOrdered;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedBasket other = (OrderedBasket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
