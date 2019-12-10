package com.formation.services.common.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.formation.exceptions.TrucNotFoundException;
import com.formation.services.common.IServiceActions;

@Transactional
public abstract class AbstractService<T> implements IServiceActions<T> {

	public abstract JpaRepository<T, Long> getRepo();
	
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return getRepo().findAll();
	}

	@Override
	public T findOne(Long id) {
		// TODO Auto-generated method stub
		Optional<T> opt = getRepo().findById(id);
		if (opt.isPresent()) return opt.get();
		throw new TrucNotFoundException( id + " pas trouve");
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
			getRepo().deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	@Override
	public boolean delete(T t) {
		// TODO Auto-generated method stub
		try {
			getRepo().delete(t);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	@Override
	public T save(T t) {
		// TODO Auto-generated method stub
		return getRepo().save(t);
	}

}
