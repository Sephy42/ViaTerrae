package com.formation.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.persistence.entities.BasketedProduct;
import com.formation.persistence.entities.Product;
import com.formation.persistence.repository.IBasketedProductRepository;
import com.formation.persistence.repository.IProductRepository;
import com.formation.services.IProductService;
import com.formation.services.common.implementation.AbstractService;

@Service
@Transactional
public class ProductService extends AbstractService<Product> implements IProductService {

	@Autowired
	private IProductRepository repoProduct;
	
	@Autowired
	private IBasketedProductRepository repoBasketedProduct;

	@Override
	public JpaRepository<Product, Long> getRepo() {
		return this.repoProduct;
	}
	
	@Override
	public List<Product> findByLabel(String label) {
		return repoProduct.findByLabel(label);
	
	}

	@Override
	public List<BasketedProduct> findBasketedProducts(Long id) {
		return repoBasketedProduct.findAllByProduct(id);
	}

	

}
