package com.formation.services;

import com.formation.persistence.entities.Admin;
import com.formation.persistence.entities.Client;

public interface IAuthChecker {

	Client getCurrentClient();
	
	Admin getCurrentAdmin();
	

}
