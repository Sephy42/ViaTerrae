package com.formation.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

import com.formation.dto.basketType.BasketTypeFull;
import com.formation.dto.basketType.BasketTypeLight;
import com.formation.dto.product.ProductFull;
import com.formation.persistence.entities.BasketType;
import com.formation.services.IBasketTypeService;

@RestController
@RequestMapping(path = "/api/private/baskets")
public class BasketTypeController {

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	IBasketTypeService service;

	@GetMapping
	public List<BasketTypeLight> findAll(){		
	     return service.findAll()
	    		 .stream()
	    		 .map(p -> mapper.map(p,BasketTypeLight.class))
	    		 .collect(Collectors.toList());
	}
	
	@GetMapping(path="/{id}")
	public BasketTypeFull findById(@PathVariable (value= "id") Long id) {		 
		  BasketType basket= service.findOne(id);
		  BasketTypeFull FinalBasket= mapper.map(basket,BasketTypeFull.class);
		  int n = basket.getListProduct().size();
		  FinalBasket.setProductCount(n);
		  Set<ProductFull> subListProduit= new HashSet<ProductFull>();
		  if (n>2) {
			  subListProduit= service.getProductsByBasketType(id).stream().limit(2).map(produit -> mapper.map(produit,ProductFull.class)).collect(Collectors.toSet());
			  
		  }	  else {
			  subListProduit= service.getProductsByBasketType(id).stream().map(produit -> mapper.map(produit,ProductFull.class)).collect(Collectors.toSet());
			  
		  }		
		  FinalBasket.setListProduct(subListProduit);
		  return FinalBasket;	    	     
	}
	
	@DeleteMapping(path="/{id}")
	public boolean delete(@PathVariable (value= "id") Long id) {
		      return service.deleteById(id);	
	}
	
	@PostMapping
	public BasketTypeFull save(@RequestBody BasketTypeFull basket) {
		return mapper.map(service.save(mapper.map(basket, BasketType.class)),BasketTypeFull.class);
	}
	
	@GetMapping(path="/{id}/products")
	public List<ProductFull> getProductByBasketType(@PathVariable (value= "id") Long id) {
		return service.getProductsByBasketType(id).stream().map(p -> mapper.map(p,ProductFull.class)).collect(Collectors.toList());
	}	
}

