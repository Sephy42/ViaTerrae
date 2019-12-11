package com.formation.dto.clients;

import java.util.Date;


public class ClientFull {

	private Long id;
	
	private String name;
	
	private String firstName;
	
	private Date birthDate;
	
	private String email;
	
	private String phone;
	
	
	//private Set<CommandeLight> commandes;
	
	

	public ClientFull() {
		//this.commandes = new HashSet<>();
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	/*
	public Set<CommandeLight> getCommandes() {
		return commandes;
	}


	public void setCommandes(Set<CommandeLight> commandes) {
		this.commandes = commandes;
	}

*/
}
