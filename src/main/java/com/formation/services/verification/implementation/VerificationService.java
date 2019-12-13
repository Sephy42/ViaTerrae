package com.formation.services.verification.implementation;

import java.util.Calendar;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.dto.basketType.toSave.BasketTypeToSave;
import com.formation.dto.order.OrderLight;
import com.formation.persistence.entities.BasketType;
import com.formation.persistence.entities.Order;
import com.formation.persistence.entities.Product;
import com.formation.services.IBasketTypeService;
import com.formation.services.IProductService;
import com.formation.services.verification.IVerificationService;

@Service
@Transactional
public class VerificationService implements IVerificationService {
	
	// BASKET VERIFICATIONS
	
	@Autowired
	IBasketTypeService basketTypeService;
	
	@Autowired
	IProductService productService;
	
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
	
	
	
	public boolean isProductDeletable(Product product) {
		if (productService.findBasketedProducts(product.getId()).isEmpty()== true) {
			return true;			
		}
		return false;
	}


	// ORDER VERIFICATIONS
	
	@Value("${delay_before_locking_order}")
	private int delayBeforeLock;
	
	@Value("${delay_before_stopping_order}")
	private int delayBeforeStop;

	public boolean isOrderSaveable (Order order) {
		
		Calendar cal = Calendar.getInstance(Locale.FRANCE); //Create a calendar at the French locale timezone
		cal.setTime(order.getPickupDate()); // choose a precise date
		cal.add(Calendar.DAY_OF_YEAR, -delayBeforeLock); // move some days before (set by property : delayBeforeLock)
		
		return (System.currentTimeMillis() > cal.getTimeInMillis());
		
		//System.currentTimeMillis() : get current time in milliseconds without the need of a locale time zone
		//cal.getTimeInMillis() : get set time of a calendar in milliseconds without the need of a locale time zone
		// t1 > t2 : compare two values : TRUE if t1 strictly superior to t2, FALSE otherwise 
	}
	
	public boolean isOrderCreateable (Order order) {
		
		Calendar cal = Calendar.getInstance(Locale.FRANCE); //Create a calendar at the French locale timezone
		cal.setTime(order.getPickupDate()); // choose a precise date
		cal.add(Calendar.DAY_OF_YEAR, -delayBeforeStop); // move some days before (set by property : delayBeforeStop)
		
		return (System.currentTimeMillis() > cal.getTimeInMillis());
		
		//System.currentTimeMillis() : get current time in milliseconds without the need of a locale time zone
		//cal.getTimeInMillis() : get set time of a calendar in milliseconds without the need of a locale time zone
		// t1 > t2 : compare two values : TRUE if t1 strictly superior to t2, FALSE otherwise 
	}
}
