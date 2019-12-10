package com.formation.services;

import org.springframework.stereotype.Service;

import com.formation.persistence.entities.Client;
import com.formation.services.common.IServiceActions;



public interface IClientService extends IServiceActions<Client> {

	Client findByMail(String username);

}
