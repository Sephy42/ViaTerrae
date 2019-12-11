package com.formation.dto.clients;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class ClientToSave {

	@NotNull
	private String name;
	
	private String firstName;
	
	private Date birthDate;
	@NotNull
	private String email;
	
	private String phone;
	@NotNull
	private String password;

	
	
	public ClientToSave() {
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
