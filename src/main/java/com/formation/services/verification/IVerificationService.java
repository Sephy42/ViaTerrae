package com.formation.services.verification;

import com.formation.dto.basketType.toSave.BasketTypeToSave;
import com.formation.dto.order.OrderLight;
import com.formation.persistence.entities.BasketType;

public interface IVerificationService {

	public boolean isBasketSaveable(BasketTypeToSave b);
	public boolean isBasketDeletable(BasketType b);
	
	public boolean isOrderSaveable (OrderLight order);
	public boolean isOrderCreateable (OrderLight order);
	
}
