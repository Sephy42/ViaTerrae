package com.formation.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.formation.persistence.entities.Product;
import com.formation.persistence.repository.IProductRepository;
import com.formation.services.IProductService;
import com.formation.services.common.implementation.AbstractService;

@Service
public class ProductService extends AbstractService<Product> implements IProductService {

	@Autowired
	private IProductRepository repo;

	@Override
	public JpaRepository<Product, Long> getRepo() {
		return this.repo;
	}
	
	@Override
	public List<Product> findByLabel(String label) {
		return repo.findByLabel(label);
	
	}	

}
