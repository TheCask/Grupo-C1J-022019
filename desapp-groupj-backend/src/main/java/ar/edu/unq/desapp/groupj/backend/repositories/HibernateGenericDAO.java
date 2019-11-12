package ar.edu.unq.desapp.groupj.backend.repositories;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * Generic hibernate DAO
 *
 * @param <T>
 */
public abstract class HibernateGenericDAO<T> extends HibernateDaoSupport implements GenericRepository<T>, Serializable {

	public static Logger log = Logger.getLogger(UserRepository.class);
	private static final long serialVersionUID = 5058950102420892922L;
	protected Class<T> persistentClass = this.getDomainClass();

	@SuppressWarnings("unchecked")
	public int count() {
		List<Long> list = (List<Long>) this.getHibernateTemplate()
				.find("select count(*) from " + this.persistentClass.getName() + " o");
		Long count = list.get(0);
		return count.intValue();
	}
	
	public List<T> findByPropertyColumn(String propertyColumn, Object object, String compare){
		return (List<T>) this.getHibernateTemplate()
		.find("FROM " + this.persistentClass.getName() + " o " + " WHERE " + propertyColumn + " " +compare+ " " + object);
	}

	public void delete(final T entity) {
		// EJEMPLO DE COMO USAR LOG4J CON ERROR
		try {
			this.getHibernateTemplate().delete(entity);
		} catch (RuntimeException e) {
			log.error(e);
		}
	}

	public void deleteById(final Serializable id) {
		T obj = this.findById(id);
		this.getHibernateTemplate().delete(obj);
	}

	public List<T> findAll() {
		List<T> find = (List<T>) this.getHibernateTemplate().find("from " + this.persistentClass.getName() + " o");
		return find;
	}

	public List<T> findByExample(final T exampleObject) {
		return this.getHibernateTemplate().findByExample(exampleObject);

	}

	public T findById(final Serializable id) {
		return this.getHibernateTemplate().get(this.persistentClass, id);
	}

	protected abstract Class<T> getDomainClass();

	public Serializable save(final T entity) {
		Serializable id;
		
		id = this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
		
		return id;
	}

	public void update(final T entity) {
		this.getHibernateTemplate().update(entity);
	}
	
	public List<T> findByPropertyLikeValue(final String propertyName, final String value) {
		DetachedCriteria criteria = DetachedCriteria.forClass(this.persistentClass);
		criteria.add( Restrictions.like(propertyName, value) );
		return (List<T>)this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	public List<Object> getElement(Object valorAComparar, String propertyNameToCompare, String propertyColumnToGet){
		DetachedCriteria criteria = DetachedCriteria.forClass(this.persistentClass);
		criteria.setProjection(Projections.property(propertyColumnToGet));
		criteria.add( Restrictions.eq(propertyNameToCompare,valorAComparar) );
		return (List<Object>)this.getHibernateTemplate().findByCriteria(criteria);
//		DetachedCriteria criteria = DetachedCriteria.forClass(this.persistentClass);
//		criteria.setProjection(Projections.property("id"));
//		criteria.add( Restrictions.eq("emailAddress",mailQueMePasan) );
//		return (List<Integer>)this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	

}