package com.formation.services;

import com.formation.persistence.entities.Admin;
import com.formation.services.common.IServiceActions;



public interface IAdminService extends IServiceActions<Admin> {

	Admin findByMail(String username);

}
