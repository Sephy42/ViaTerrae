package com.formation.services;

import java.util.List;
import java.util.Set;

import com.formation.persistence.entities.BasketType;
import com.formation.persistence.entities.BasketedProduct;
import com.formation.persistence.entities.OrderedBasket;
import com.formation.services.common.IServiceActions;


public interface IBasketTypeService extends IServiceActions<BasketType>{
	Set<BasketedProduct> getProductListByBasketType (Long id);

     /**
     * @param id of basketType
     * @return List of ordered basket 
     * find ordered basket by the basketType id
     */	
    List<OrderedBasket> findOrderedBaskets(Long id);
}
