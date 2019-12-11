package com.formation.dto.pickUpDate;

import java.util.Date;

public class PickUpDateFull {
	
	
		
		Long id;
		Date date;
		int minutesStart;
		int minutesEnd;
		
		public PickUpDateFull() {
		
		
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
