package com.formation.dto.product;

import com.formation.dto.category.CategoryFull;


public class ProductFull {
	
	 private	Long id;
	 private	String label;
	 private	CategoryFull category;
	 
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
	public CategoryFull getCategory() {
		return category;
	}
	public void setCategory(CategoryFull category) {
		this.category = category;
	}
	 
 
	
}
