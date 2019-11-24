package ar.edu.unq.desapp.groupj.backend.repositories;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for generic DAO
 * 
 * @param <T>
 */
public interface GenericRepository<T> {

	Serializable save(T entity);

	void delete(T entity);

	void update(T entity);

	T findById(Serializable id);

	List<T> findAll();

	void deleteById(Serializable id);

	int count();

	List<T> findByExample(T exampleObject);
	
	List<T> findByPropertyLikeValue(String propertyName, String value);

	List<T> findByTextInProperties(List<String> propertiesNames, String value, boolean ignoreCase);
	
	List<Object> getElement(Object valueToCompare, String propertyNameToCompare, String propertyColumnToGet);

	List<T> findByPropertyColumn(String propertyColumn, Object object, String compare);
}