package com.formation.services.common.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.formation.exceptions.NotFoundException;
import com.formation.services.common.IServiceActions;

@Transactional
public abstract class AbstractService<T> implements IServiceActions<T> {

	public abstract JpaRepository<T, Long> getRepo();
	
	@Override
	public List<T> findAll() {
		return getRepo().findAll();
	}

	@Override
	public T findOne(Long id) {
		Optional<T> opt = getRepo().findById(id);
		if (opt.isPresent()) return opt.get();
		throw new NotFoundException( id + " pas trouve");
	}

	@Override
	public boolean deleteById(Long id) {
		try {
			getRepo().deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(T t) {
		try {
			getRepo().delete(t);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public T save(T t) {

		return getRepo().save(t);
	}

}
