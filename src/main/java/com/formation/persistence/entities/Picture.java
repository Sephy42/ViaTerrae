package com.formation.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table (name = "pictures") @Entity
public class Picture {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;
	@Column (unique = true, nullable = false)
	byte [] picture;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
}
