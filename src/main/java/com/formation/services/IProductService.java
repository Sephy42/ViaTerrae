package com.formation.services;

import java.util.List;

import com.formation.persistence.entities.Product;
import com.formation.services.common.IServiceActions;

public interface IProductService extends IServiceActions<Product> {

		List<Product> findByLabel(String Label);
}
