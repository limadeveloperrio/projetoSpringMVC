package br.com.cotiinformatica.interfaces;

import java.util.List;

public interface IBaseRepository<T, ID> {

	void create(T obj) throws Exception;

	void update(T obj) throws Exception;
	
	void delete(T obj) throws Exception;
	
	List<T> getAll() throws Exception;
	
	T getById(ID id) throws Exception;
}
