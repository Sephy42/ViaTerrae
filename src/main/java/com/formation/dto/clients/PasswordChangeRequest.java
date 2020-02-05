package com.formation.dto.clients;

public class PasswordChangeRequest {

	private String oldOne;
	private String newOne;
	
	public String getOldOne() {
		return oldOne;
	}
	public void setOldOne(String oldOne) {
		this.oldOne = oldOne;
	}
	public String getNewOne() {
		return newOne;
	}
	public void setNewOne(String newOne) {
		this.newOne = newOne;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordChangeRequest other = (PasswordChangeRequest) obj;
		if (newOne == null) {
			if (other.newOne != null)
				return false;
		} else if (!newOne.equals(other.newOne))
			return false;
		if (oldOne == null) {
			if (other.oldOne != null)
				return false;
		} else if (!oldOne.equals(other.oldOne))
			return false;
		return true;
	}
	
	
	
	
}
