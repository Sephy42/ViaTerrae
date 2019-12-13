package com.formation.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.dto.basketType.toSave.BasketTypeToSave;
import com.formation.persistence.entities.BasketType;
import com.formation.services.IBasketTypeService;
import com.formation.services.IVerificationBasketTypeService;

@Service
@Transactional
public class VerificationBasketTypeService implements IVerificationBasketTypeService{
	
	@Autowired
	IBasketTypeService basketTypeService;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public boolean isBasketSaveable(BasketTypeToSave b) {
		if ((b.getId()!=null) && (basketTypeService.findOrderedBaskets(b.getId()).isEmpty()== false)) {
			BasketType basketToModify = basketTypeService.findOne(b.getId());
			if ((b.getLabel().equals(basketToModify.getLabel())) && (basketTypeService.equals(b.getListProductToSave(), basketToModify.getListProduct())==true)) {
				return true;
			}else {
				return false;
			}			
		}else {
			return true;}
	}

	@Override
	public boolean isBasketDeletable(BasketType b) {
		if (basketTypeService.findOrderedBaskets(b.getId()).isEmpty()== true) {
			return true;			
		}
		return false;
	}



}
