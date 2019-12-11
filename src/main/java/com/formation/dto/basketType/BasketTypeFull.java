package com.formation.dto.basketType;

import java.util.Date;
import java.util.Set;

import com.formation.dto.product.ProductFull;
import com.formation.dto.product.UsedProduct;
import com.formation.persistence.entities.BasketedProduct;
import com.formation.persistence.entities.Picture;


public class BasketTypeFull {

	private Long id;
	private Date beginDate;
	private Date endDate;
	private Double cost;
	private int ProductCount;
	private Integer quantityAvailable;
	private String label;
	
	private Set<UsedProduct> listProduct;
	private Picture picture;
	
	public BasketTypeFull() {
	}

	public BasketTypeFull(Long id, Date beginDate, Date endDate, Double cost, int productCount,
			Integer quantityAvailable, String label, Set<UsedProduct> listProduct, Picture picture) {
		this.id = id;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.cost = cost;
		ProductCount = productCount;
		this.quantityAvailable = quantityAvailable;
		this.label = label;
		this.listProduct = listProduct;
		this.picture = picture;
	}



	public Picture getPicture() {
		return picture;
	}

	public int getProductCount() {
		return ProductCount;
	}

	public void setProductCount(int productCount) {
		ProductCount = productCount;
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

	public Set<UsedProduct> getListProduct() {
		return listProduct;
	}

	public void setListProduct(Set<UsedProduct> listProduct) {
		this.listProduct = listProduct;
	}


	
}
