package com.formation.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.persistence.entities.Picture;
import com.formation.persistence.repository.IPictureRepository;
import com.formation.services.IPictureService;
import com.formation.services.common.implementation.AbstractService;

<<<<<<< HEAD

@Service
@Transactional
=======
@Service
@Transactional

>>>>>>> branch 'devel' of https://github.com/Sephy42/ViaTerrae.git
public class PictureService extends AbstractService<Picture> implements IPictureService  {
	
	@Autowired
	private IPictureRepository repo;
	
	@Override
	public JpaRepository<Picture, Long> getRepo(){
		return repo;
	}
	
	
}
