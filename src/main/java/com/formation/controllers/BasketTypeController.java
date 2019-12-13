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
import com.formation.exceptions.NotAuthorizedException;
import com.formation.persistence.entities.BasketType;
import com.formation.persistence.entities.BasketedProduct;
import com.formation.persistence.entities.Picture;
import com.formation.services.IAuthChecker;
import com.formation.services.IBasketTypeService;
import com.formation.services.IPictureService;
import com.formation.services.IProductService;
import com.formation.services.IVerificationBasketTypeService;



@RestController
@RequestMapping(path = "/api/private/baskets")
public class BasketTypeController {

	@Autowired
	ModelMapper mapper;

	@Autowired
	IBasketTypeService basketTypeService;

	@Autowired
	IProductService serviceProduct;

	@Autowired
	IPictureService servicePicture;

	@Autowired
	private IAuthChecker authChecker;

	@Autowired
	private IVerificationBasketTypeService verificationService;

	/**
	 * Find all basket type 
	 * this action is only available for the administrator
	 */
	@GetMapping
	public List<BasketTypeLight> findAll(){	
		if (authChecker.getCurrentAdmin() != null ) {
			return basketTypeService.findAll()
					.stream()
					.map(p -> mapper.map(p,BasketTypeLight.class))
					.collect(Collectors.toList());
		} 
		throw new NotAuthorizedException();	
	}

	/**
	 * @param id
	 * @return BasketTypeFull
	 * Find a basketType 
	 */
	@GetMapping(path="/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public BasketTypeFull findById(@PathVariable (value= "id") Long id) {		 
		BasketType basket= basketTypeService.findOne(id);
		BasketTypeFull FinalBasket= mapper.map(basket,BasketTypeFull.class);
		int n = basket.getListProduct().size();
		FinalBasket.setProductCount(n);
		return FinalBasket;	    	     
	}


	@DeleteMapping(path="/{id}")
	public boolean delete(@PathVariable (value= "id") Long id) {
		if (authChecker.getCurrentAdmin() != null ) {
			if(verificationService.isBasketDeletable(basketTypeService.findOne(id))==true) {
				return  basketTypeService.deleteById(id);
			}else {
				throw new NotAuthorizedException("Could not delete, because the basket is already ordered");
			}
		} 
		throw new NotAuthorizedException("Not authorized");	
	}

	/**
	 * @param basket
	 * @return BasketTypeFul
	 * create a basketType
	 * or modify the basketType if there is an Id in the basketTypeToSave
	 * if the basketType is already in an order, the admin can only modify the quantity available and the cost else throw an exception
	 * this action is only allowed to the administrator and throw an exception if launched by any other user
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public BasketTypeFull save(@RequestBody BasketTypeToSave basket) {

		if (authChecker.getCurrentAdmin() != null ) {

			if (verificationService.isBasketSaveable(basket)==true) {
				BasketType basketToSave = mapper.map(basket,BasketType.class);
				basketToSave.getListProduct().clear();
				basket.getListProductToSave().stream().forEach(productToSave ->{
					basketToSave.getListProduct()
					.add(new BasketedProduct(productToSave.getQuantity(), productToSave.getUnit(),serviceProduct.findOne(productToSave.getProductId())));	
				});
				BasketType basketSaved = basketTypeService.save(basketToSave);
				return mapper.map(basketSaved, BasketTypeFull.class);
			} else {
				throw new NotAuthorizedException("You are trying to modify something in a basket already ordered");
			}
		}else {
		throw new NotAuthorizedException("Not authorized");
		}
	}

	/**
	 * @param picture
	 * @param basketId
	 * add a picture to a basketType
	 * this action is only allowed to the administrator and throw an exception if launched by any other user
	 */
	@PostMapping(path="/{id}/picture")
	@ResponseStatus(code = HttpStatus.OK)
	public void addPictureToBasketType(@RequestBody PictureToSave picture, @PathVariable (value= "id") Long basketId) {					
		if (authChecker.getCurrentAdmin() != null ) {
			byte imageData[] = Base64.getDecoder().decode(picture.getPicture());

			Picture p = new Picture();
			p.setPicture(imageData);


			BasketType basket = basketTypeService.findOne(basketId);
			basket.setPicture(p);
			basketTypeService.save(basket);
		}else {
		throw new NotAuthorizedException();
		}
	}

	/**
	 * @param basketId
	 * delete a picture attached to a basketType
	 * this action is only allowed to the administrator and throw an exception if launched by any other user
	 */
	@DeleteMapping(path="/{id}/picture")
	@ResponseStatus(code = HttpStatus.OK)
	public void deletePictureToBasketType(@PathVariable (value= "id") Long basketId) {	

		if (authChecker.getCurrentAdmin() != null ) {

			BasketType basket = basketTypeService.findOne(basketId);
			basket.setPicture(null);
			basketTypeService.save(basket);
		}else {
		throw new NotAuthorizedException();
		}

	}
	//
	/**
	 * @param id
	 * @return Set of UsedProduct (quantity,unit, product) in a basket
	 */
	@GetMapping(path="/{id}/products")
	@ResponseStatus(code = HttpStatus.OK)
	public Set<UsedProduct> getProductListByBasketType(@PathVariable (value= "id") Long id) {

		return basketTypeService.getProductListByBasketType(id)
				.stream()
				.map(basketedProduct -> new UsedProduct(basketedProduct.getQuantity(),basketedProduct.getUnit(),mapper.map(basketedProduct.getProduct(),ProductFull.class)))
				.collect(Collectors.toSet());

	}
}

