package com.formation.services;

<<<<<<< HEAD
import org.springframework.stereotype.Service;
=======
import org.springframework.transaction.annotation.Transactional;
>>>>>>> branch 'devel' of https://github.com/Sephy42/ViaTerrae.git

import com.formation.persistence.entities.Client;
import com.formation.services.common.IServiceActions;

<<<<<<< HEAD


=======
@Transactional
>>>>>>> branch 'devel' of https://github.com/Sephy42/ViaTerrae.git
public interface IClientService extends IServiceActions<Client> {

	Client findByMail(String username);

}
