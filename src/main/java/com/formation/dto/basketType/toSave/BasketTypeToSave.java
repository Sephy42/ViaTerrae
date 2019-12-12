package com.formation.dto.basketType.toSave;

import java.util.Date;
import java.util.Set;


public class BasketTypeToSave {

	private Long id;
	private Date beginDate;
	private Date endDate;
	private Double cost;
	private Integer quantityAvailable;
	private String label;	
	private  Set<UsedProduct> listProductToSave;

	
	
	public BasketTypeToSave(){
		this.id=null;
		
	}
	
	
	
	public BasketTypeToSave(Date beginDate, Date endDate, Double cost, Integer quantityAvailable, String label,
			Set<UsedProduct> listProductToSave) {

		this.beginDate = beginDate;
		this.endDate = endDate;
		this.cost = cost;
		this.quantityAvailable = quantityAvailable;
		this.label = label;
		this.listProductToSave = listProductToSave;

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
	public Set<UsedProduct> getListProductToSave() {
		return listProductToSave;
	}
	public void setListProductToSave(Set<UsedProduct> listProductToSave) {
		this.listProductToSave = listProductToSave;
	}

	

	

	
}
