package com.formation.dto.order;

import com.formation.dto.basketType.BasketTypeLight;

public class OrderedBasketForOrderFull {
	
	Long id;
	Double quantity;
	BasketTypeLight basket;
	Double costOrdered;
	
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
	public BasketTypeLight getBasket() {
		return basket;
	}
	public void setBasket(BasketTypeLight basket) {
		this.basket = basket;
	}
	public Double getCostOrdered() {
		return costOrdered;
	}
	public void setCostOrdered(Double costOrdered) {
		this.costOrdered = costOrdered;
	}
	
}
