package com.formation.dto.product;

public class UsedProduct {

	Long id;
	Double quantity;
	String unit;
	ProductFull product;

	public UsedProduct() {
		
	}
	
	public UsedProduct(Double quantity, String unit, ProductFull product) {
		this.quantity = quantity;
		this.unit = unit;
		this.product = product;
	}


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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public ProductFull getProduct() {
		return product;
	}

	public void setProduct(ProductFull product) {
		this.product = product;
	}




	
}
