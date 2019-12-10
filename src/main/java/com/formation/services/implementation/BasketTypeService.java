package com.formation.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.persistence.entities.BasketType;
import com.formation.persistence.entities.Product;
import com.formation.persistence.repository.IBasketTypeRepository;
import com.formation.services.IBasketTypeService;
import com.formation.services.common.implementation.AbstractService;

@Service
@Transactional
	public class  BasketTypeService extends AbstractService<BasketType>  implements IBasketTypeService{

		@Autowired
		IBasketTypeRepository repo;


		@Override
		public JpaRepository<BasketType, Long> getRepo() {
			return repo;
		}



		@Override
		public List<Product> getProductsByBasketType(Long id) {
			BasketType basket= findOne(id);
			List<Product> productList= new ArrayList<Product>();
			basket.getListProduct().stream().forEach(basketedProduct -> productList.add(basketedProduct.getProduct()));	
			return productList;			
		}


	}


