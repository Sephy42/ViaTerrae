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

@Table (name = "basketed_products") @Entity
public class BasketedProduct {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;
	@Column (nullable = false)
	Double quantity;
	@Column (nullable = false)
	String unit;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id",referencedColumnName = "id", nullable = false)
	Product product;
	
	public BasketedProduct() {
		
	}

	public BasketedProduct(Double quantity, String unit, Product product) {
		super();
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
}
