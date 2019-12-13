package com.formation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.product.ProductFull;
import com.formation.exceptions.NotAuthorizedException;
import com.formation.persistence.entities.Product;
import com.formation.services.IAuthChecker;
import com.formation.services.IProductService;
import com.formation.services.verification.IVerificationService;

@RestController
@RequestMapping(path = "/api/private/products")

public class ProductController {

	@Autowired
	IProductService servProduct;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IAuthChecker authChecker;

	@Autowired
	private IVerificationService verificationService;
	
	@GetMapping 
	public List<ProductFull> findAll() {
		if (authChecker.getCurrentAdmin() != null ) {
			return servProduct.findAll()
					.stream()
					.map(p -> mapper.map(p , ProductFull.class))
					.collect(Collectors.toList());
		}
		throw new NotAuthorizedException();	
	}
	
	@GetMapping(path = "/{id}")
	public ProductFull findOne (@PathVariable Long id) {
		if (authChecker.getCurrentAdmin() != null ) {
			return mapper.map(servProduct.findOne(id), ProductFull.class);
		}
		throw new NotAuthorizedException();	
	}
	
	
	@DeleteMapping (path = "/{id}")
	public boolean delete (@PathVariable Long id) {
		if (authChecker.getCurrentAdmin() != null ) { 
			if(verificationService.isProductDeletable(servProduct.findOne(id))==true) {
				return  servProduct.deleteById(id);
			}else {
				throw new NotAuthorizedException("Could not delete, because the basket is already ordered");
			}
		}
		throw new NotAuthorizedException();	
	}
	
	@PostMapping
	public ProductFull save (@RequestBody ProductFull product) {
		if (authChecker.getCurrentAdmin() != null ) { 
			Product p = mapper.map(product, Product.class);
			
			p = servProduct.save(p);
			
			return mapper.map(p, ProductFull.class);
		}
		throw new NotAuthorizedException();	
	}
	
	
	
}