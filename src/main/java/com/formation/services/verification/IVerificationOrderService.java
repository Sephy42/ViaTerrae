package com.formation.services.verification;

import com.formation.dto.order.OrderLight;

public interface IVerificationOrderService {
	
	public boolean isOrderSaveable (OrderLight order);
	public boolean isOrderCreateable (OrderLight order);
}
