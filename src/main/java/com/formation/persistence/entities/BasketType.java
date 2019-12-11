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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table (name = "basket_types") @Entity
public class BasketType {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;
	@Column (nullable = false)
	Date beginDate;
	@Column (nullable = false)
	Date endDate;
	@Column (nullable = false)
	Double cost;
	@Column (nullable = false)
	Integer quantityAvailable;
	@Column
	String label;
	
	@OneToMany(orphanRemoval = true,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "basket_id",referencedColumnName = "id", nullable = false)
	private Set<BasketedProduct> listProduct;
	
	@OneToOne(orphanRemoval = true,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "picture_id",referencedColumnName = "id",nullable = true)
	private Picture picture;

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Set<BasketedProduct> getListProduct() {
		return listProduct;
	}

	public void setListProduct(Set<BasketedProduct> listProduct) {
		this.listProduct = listProduct;
	}
	
	
}
