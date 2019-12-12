package com.formation.dto.pickUpDate;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class PickUpDateToSave {

	
	private Long id;
	
	@NotNull
	private Date date;
	
	@NotNull
	private int minutesStart;
	
	@NotNull
	private int minutesEnd;

	
	
	public PickUpDateToSave() {
	}



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
