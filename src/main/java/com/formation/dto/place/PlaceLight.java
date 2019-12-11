package com.formation.dto.place;


import javax.persistence.Entity;
import javax.persistence.Table;


public class PlaceLight {
	
	Long id;
	String label;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
}
