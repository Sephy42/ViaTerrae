package com.formation.services.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.dto.basketType.toSave.UsedProduct;
import com.formation.persistence.entities.BasketType;
import com.formation.persistence.entities.BasketedProduct;
import com.formation.persistence.entities.OrderedBasket;
import com.formation.persistence.repository.IBasketTypeRepository;
import com.formation.persistence.repository.IOrderedBasketRepository;
import com.formation.services.IBasketTypeService;
import com.formation.services.common.implementation.AbstractService;


@Service
@Transactional
	public class  BasketTypeService extends AbstractService<BasketType>  implements IBasketTypeService{

		@Autowired
		IBasketTypeRepository repo;
		@Autowired
		IOrderedBasketRepository orderedBasketrepo;

		@Override
		public JpaRepository<BasketType, Long> getRepo() {
			return repo;
		}


		/**
		 *find the list of basketedProduct in a basketType
		 */
		@Override
		public Set<BasketedProduct> getProductListByBasketType(Long id) {
			BasketType basket= findOne(id);	
			return basket.getListProduct();			
		}


		@Override
		public List<OrderedBasket> findOrderedBaskets(Long id) {
			
			return orderedBasketrepo.findAllByBasketTypeId(id);
		}


		@Override
		public boolean equals(Set<UsedProduct> setUsedProduct, Set<BasketedProduct> setBasketedProduct) {
			Set<UsedProduct> setUsedProduct2= new HashSet<UsedProduct>();
			setBasketedProduct.stream().forEach(basketedProduct -> 
			setUsedProduct2.add(new UsedProduct(basketedProduct.getQuantity(), basketedProduct.getUnit(), basketedProduct.getProduct().getId())));
			if (setUsedProduct.equals(setUsedProduct2)) {
				return true;
			}
			return false;
		}


	}


