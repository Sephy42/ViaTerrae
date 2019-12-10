package com.formation.dto.clients;

import java.util.Date;


public class ClientFull {

private Long id;
	
	private String nom;
	
	private String prenom;
	
	private Date naissance;
	
	private String mail;
	
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


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Date getNaissance() {
		return naissance;
	}


	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
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
