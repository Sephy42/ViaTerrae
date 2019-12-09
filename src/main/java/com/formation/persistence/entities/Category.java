package com.formation.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table (name = "categories") @Entity
public class Category {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;
	@Column (unique = true, nullable = false)
	String label;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return label;
	}
	public void setTitle(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", title=" + label + "]";
	}
	
}
