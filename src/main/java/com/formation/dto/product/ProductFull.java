package com.formation.dto.product;

import com.formation.persistence.entities.Category;


public class ProductFull {
 private	Long id;
 private	String label;
 private	Category category;
 
 
 
 public ProductFull() {
	super();
	// TODO Auto-generated constructor stub
 }
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
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}

	
	
	
}
