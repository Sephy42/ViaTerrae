package com.formation.persistence.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table (name = "pick_up_date") 
@Entity
public class PickUpDate {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;
	@Column (nullable = false)
	Date date;
	@Column (nullable = false)
	int minutesStart;
	@Column (nullable = false)
	int minutesEnd;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getMinutesStart() {
		return minutesStart;
	}
	public void setMinutesStart(int minutesStart) {
		this.minutesStart = minutesStart;
	}
	public int getMinutesEnd() {
		return minutesEnd;
	}
	public void setMinutesEnd(int minutesEnd) {
		this.minutesEnd = minutesEnd;
	}
	

	
	
}
