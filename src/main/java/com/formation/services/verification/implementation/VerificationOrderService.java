package com.formation.services.verification.implementation;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.dto.order.OrderLight;

@Service
@Transactional
public class VerificationOrderService {
	
	@Value("${delay_before_locking_order}")
	private int delayBeforeLock;
	
	@Value("${delay_before_stopping_order}")
	private int delayBeforeStop;

	public boolean isOrderSaveable (OrderLight order) {
		
		Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.add(Calendar.DAY_OF_YEAR, -delayBeforeLock);
		return order.getPickupDate().before(cal.getTime());
	}
	
	public boolean isOrderCreateable (OrderLight order) {
		
		Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.add(Calendar.DAY_OF_YEAR, -delayBeforeStop);
		return order.getPickupDate().before(cal.getTime());
	}
}
