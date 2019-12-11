package com.formation.services;

import java.util.Set;

import com.formation.persistence.entities.BasketType;
import com.formation.persistence.entities.BasketedProduct;
import com.formation.services.common.IServiceActions;

public interface IBasketTypeService extends IServiceActions<BasketType>{
	Set<BasketedProduct> getProductListByBasketType (Long id);
}
