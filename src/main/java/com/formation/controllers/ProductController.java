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
import com.formation.persistence.entities.Product;
import com.formation.services.IProductService;

@RestController
@RequestMapping(path = "/api/private/products")

public class ProductController {

	@Autowired
	IProductService service;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@GetMapping 
	public List<ProductFull> findAll() {
		return service.findAll()
				.stream()
				.map(p -> mapper.map(p , ProductFull.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping(path = "/{id}")
	public ProductFull findOne (@PathVariable Long id) {
		return mapper.map(service.findOne(id), ProductFull.class);
	}
	
	
	@DeleteMapping (path = "/{id}")
	public void delete (@PathVariable Long id) {
		service.deleteById(id);
	}
	
	@PostMapping
	public ProductFull save (@RequestBody ProductFull product) {
		
		Product p = mapper.map(product, Product.class);
		
		p = service.save(p);
		
		return mapper.map(p, ProductFull.class);
	}
	
	
	
}