package com.formation.services.verification;

import com.formation.dto.basketType.toSave.BasketTypeToSave;
import com.formation.persistence.entities.BasketType;
import com.formation.persistence.entities.Order;
import com.formation.persistence.entities.Product;

public interface IVerificationService {

	public boolean isBasketSaveable(BasketTypeToSave b);
	public boolean isBasketDeletable(BasketType b);
	
	public boolean isOrderSaveable (Order order);
	public boolean isOrderCreateable (Order order);
	public boolean isProductDeletable(Product findOne);
	
}
