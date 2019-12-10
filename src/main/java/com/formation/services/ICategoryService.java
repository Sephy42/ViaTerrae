package com.formation.services;

import java.util.List;

import com.formation.persistence.entities.Category;
import com.formation.services.common.IServiceActions;

public interface ICategoryService extends IServiceActions<Category> {

		List<Category> findByTitle(String Label);
}
