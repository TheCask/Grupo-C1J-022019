package ar.edu.unq.desapp.groupj.backend.services;

import java.io.Serializable;
import java.util.List;
import ar.edu.unq.desapp.groupj.backend.repositories.GenericRepository;
import org.springframework.transaction.annotation.Transactional;


public class GenericService<T> implements Serializable {

    private static final long serialVersionUID = -6540963495078524186L;
    private GenericRepository<T> repository;

    public GenericRepository<T> getRepository() {
        return this.repository;
    }

    public void setRepository(final GenericRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    public void delete(final T object) {
        this.getRepository().delete(object);
    }
    
    @Transactional
    public void deleteById(Serializable id) {
        this.getRepository().deleteById(id);
    }
    
    @Transactional
    public T findById(Serializable id) {
        return this.getRepository().findById(id);
    }

    @Transactional(readOnly = true)
    public List<T> retriveAll() {
        return this.getRepository().findAll();
    }

    @Transactional
    public Serializable save(final T object) {
        return this.getRepository().save(object);
    }

    @Transactional
    public void update(final T object) {
        this.getRepository().update(object);
    }
    
    @Transactional
    public List<T> findByPropertyLikeValue(String propertyName, String value) {
    	return this.getRepository().findByPropertyLikeValue(propertyName, value);
    }
    
    @Transactional
    //public List<Integer> getProperty(String mail){
    public List<Object> getProperty(Object valorAComparar, String propertyNameToCompare, String propertyColumnToGet){
    	return this.getRepository().getElement(valorAComparar,propertyNameToCompare,propertyColumnToGet);
    }
    
    @Transactional
    public List<Object> findByPropertyColumn(String propertyColumn, Object object, String compare){
    	return this.getRepository().findByPropertyColumn(propertyColumn, object, compare);
    }


}
