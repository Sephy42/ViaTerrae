package com.formation.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.dto.basketType.toSave.BasketTypeToSave;
import com.formation.persistence.entities.BasketType;
import com.formation.services.IBasketTypeService;
import com.formation.services.IVerificationService;

@Service
@Transactional
public class VerificationService implements IVerificationService{
	
	@Autowired
	IBasketTypeService basketTypeService;

	@Override
	public boolean isBasketSaveable(BasketTypeToSave b) {
		//TODO
		return true;
	}

	@Override
	public boolean isBasketDeletable(BasketType b) {
		// TODO Auto-generated method stub
		return true;
	}



}
