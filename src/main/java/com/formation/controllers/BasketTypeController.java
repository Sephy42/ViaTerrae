package com.formation.controllers;

import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.basketType.BasketTypeFull;
import com.formation.dto.basketType.BasketTypeLight;
import com.formation.dto.basketType.toSave.BasketTypeToSave;
import com.formation.dto.picture.PictureToSave;
import com.formation.dto.product.ProductFull;
import com.formation.dto.product.UsedProduct;
import com.formation.persistence.entities.BasketType;
import com.formation.persistence.entities.BasketedProduct;
import com.formation.persistence.entities.Picture;
import com.formation.services.IBasketTypeService;
import com.formation.services.IPictureService;
import com.formation.services.IProductService;

@RestController
@RequestMapping(path = "/api/public/baskets")
public class BasketTypeController {

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	IBasketTypeService service;
	
	@Autowired
	IProductService serviceProduct;
	
	@Autowired
	IPictureService servicePicture;
	
	@GetMapping
	public List<BasketTypeLight> findAll(){		
	     return service.findAll()
	    		 .stream()
	    		 .map(p -> mapper.map(p,BasketTypeLight.class))
	    		 .collect(Collectors.toList());
	}
	// trouve le panier le retourne, avec une liste réduite de produit visibles
	@GetMapping(path="/{id}")
	public BasketTypeFull findById(@PathVariable (value= "id") Long id) {		 
		  BasketType basket= service.findOne(id);
		  BasketTypeFull FinalBasket= mapper.map(basket,BasketTypeFull.class);
		  int n = basket.getListProduct().size();
		  FinalBasket.setProductCount(n);
		  Set<UsedProduct> subListProduit= getProductListByBasketType(id);
		  if (n>2) {
			  subListProduit.stream().limit(2);			  
		  }	  		
		  FinalBasket.setListProduct( subListProduit);
		  return FinalBasket;	    	     
	}
	
	@DeleteMapping(path="/{id}")
	public boolean delete(@PathVariable (value= "id") Long id) {
		      return service.deleteById(id);	
	}
	// modifie ou crée un panier
	@PostMapping
	public BasketTypeFull save(@RequestBody BasketTypeToSave basket) {
		BasketType basketToSave = mapper.map(basket,BasketType.class);
		basketToSave.getListProduct().clear();
		basket.getListProductToSave().stream().forEach(productToSave ->{
			basketToSave.getListProduct()
			.add(new BasketedProduct(productToSave.getQuantity(), productToSave.getUnit(),serviceProduct.findOne(productToSave.getProductId())));	
		});
		BasketType basketSaved = service.save(basketToSave);
		return mapper.map(basketSaved, BasketTypeFull.class);
	}

	// ajoute une photo au panier
	@PostMapping(path="/{id}/picture")
	@ResponseStatus(code = HttpStatus.OK)
	public void addPictureToBasketType(@RequestBody PictureToSave picture, @PathVariable (value= "id") Long basketId) {					
		
		byte imageData[] = Base64.getDecoder().decode(picture.getPicture());
		
		Picture p = new Picture();
		p.setPicture(imageData);
		
		
		BasketType basket = service.findOne(basketId);
		basket.setPicture(p);
		service.save(basket);
	}
	
	@GetMapping(path="/{id}/products")
	public Set<UsedProduct> getProductListByBasketType(@PathVariable (value= "id") Long id) {
		return service.getProductListByBasketType(id)
				.stream()
				.map(basketedProduct -> new UsedProduct(basketedProduct.getQuantity(),basketedProduct.getUnit(),mapper.map(basketedProduct.getProduct(),ProductFull.class)))
				.collect(Collectors.toSet());

	}
}

