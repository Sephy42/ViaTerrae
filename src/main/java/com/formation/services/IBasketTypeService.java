package com.formation.services;

import java.util.List;

import com.formation.persistence.entities.BasketType;
import com.formation.persistence.entities.Product;
import com.formation.services.common.IServiceActions;

public interface IBasketTypeService extends IServiceActions<BasketType>{
	List<Product> getProductsByBasketType (Long id);
}
