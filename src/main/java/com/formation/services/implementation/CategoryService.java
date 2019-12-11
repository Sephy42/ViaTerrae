package com.formation.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.persistence.entities.Category;
import com.formation.persistence.repository.ICategoryRepository;
import com.formation.services.ICategoryService;
import com.formation.services.common.implementation.AbstractService;

@Service
@Transactional
public class CategoryService extends AbstractService<Category> implements ICategoryService {

	@Autowired
	private ICategoryRepository repo;

	@Override
	public JpaRepository<Category, Long> getRepo() {
		return this.repo;
	}
	
	@Override
	public List<Category> findByTitle(String label) {
		return repo.findByTitle(label);
	
	}	

}
