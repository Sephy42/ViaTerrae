package com.formation.services.common;

import java.util.List;

public interface IServiceActions<T> {
	
	public List<T> findAll ();
	public T findOne (Long id);
	public boolean deleteById (Long id);
	public boolean delete (T t);
	public T save (T t);

}
