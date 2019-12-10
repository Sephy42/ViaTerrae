package com.formation.services;

import org.springframework.transaction.annotation.Transactional;

import com.formation.persistence.entities.Client;
import com.formation.services.common.IServiceActions;

@Transactional
public interface IClientService extends IServiceActions<Client> {

	Client findByMail(String username);

}
