package com.formation.dto.order;

public class OrderedBasketForOrderLight {

	Long id;
	Double quantity;
	Long basket;
	
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
	public Long getBasket() {
		return basket;
	}
	public void setBasket(Long basket) {
		this.basket = basket;
	}
}
