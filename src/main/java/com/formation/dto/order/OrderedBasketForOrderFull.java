package com.formation.dto.order;

import com.formation.dto.basketType.BasketTypeFull;

public class OrderedBasketForOrderFull {
	
	Long id;
	Double quantity;
	BasketTypeFull basket;
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
	public BasketTypeFull getBasket() {
		return basket;
	}
	public void setBasket(BasketTypeFull basket) {
		this.basket = basket;
	}
	public Double getCostOrdered() {
		return costOrdered;
	}
	public void setCostOrdered(Double costOrdered) {
		this.costOrdered = costOrdered;
	}
	
}
