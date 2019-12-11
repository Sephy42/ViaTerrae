package com.formation.dto.basketType.toSave;

public class UsedProduct {

	Long id;
	Integer quantity;
	String unit;
	Long productId;

	public UsedProduct() {
		
	}
	
	public UsedProduct(Integer quantity, String unit, Long productId) {
		this.quantity = quantity;
		this.unit = unit;
		this.productId = productId;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}





	
}
