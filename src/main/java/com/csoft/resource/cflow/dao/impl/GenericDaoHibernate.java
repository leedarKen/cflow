package com.csoft.resource.cflow.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.csoft.resource.cflow.dao.GenericDao;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *      &lt;bean id="fooDao" class="com.csoft.resource.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.csoft.resource.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	private final Log log = LogFactory.getLog(getClass());
	private Class<T> persistentClass;
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;

	/**
	 * Constructor that takes in a class to see which type of entity to persist.
	 * Use this constructor when subclassing.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDaoHibernate(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * Constructor that takes in a class and sessionFactory for easy creation of
	 * DAO.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 * @param sessionFactory
	 *            the pre-configured Hibernate SessionFactory
	 */
	public GenericDaoHibernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
		this.persistentClass = persistentClass;
		this.sessionFactory = sessionFactory;
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@Autowired
	@Required
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return hibernateTemplate.loadAll(this.persistentClass);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllDistinct() {
		Collection result = new LinkedHashSet(getAll());
		return new ArrayList(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		T entity = (T) hibernateTemplate.get(this.persistentClass, id);

		if (entity == null) {
			log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(this.persistentClass, id);
		}

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(PK id) {
		T entity = (T) hibernateTemplate.get(this.persistentClass, id);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T save(T object) {
		return (T) hibernateTemplate.merge(object);
	}
	
	public Serializable save2(T object){
		return hibernateTemplate.save(object);
	}
	
	public void update(T object){
		hibernateTemplate.update(object);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		hibernateTemplate.delete(this.get(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
		String[] params = new String[queryParams.size()];
		Object[] values = new Object[queryParams.size()];

		int index = 0;
		for (String s : queryParams.keySet()) {
			params[index] = s;
			values[index++] = queryParams.get(s);
		}

		return hibernateTemplate.findByNamedQueryAndNamedParam(queryName, params, values);
	}

	/**
	 * query by different criteria
	 * 
	 * @author robinson.wang
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {
		return hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
	}

	@SuppressWarnings("unchecked")
	public List<Object> findBySQL(String sqlStr,Map<String, Object> parameters, int firstResult, int maxResults) {
		Session session = sessionFactory.getCurrentSession();

		// Transaction trans =session.beginTransaction();
		if(firstResult>-1 && maxResults>0){
			sqlStr += " limit " + firstResult + " ," + maxResults + ";";
		}
		
		Query query = session.createSQLQuery(sqlStr);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.list();

	}

	/**
	 * query by different criteria
	 * 
	 * @author robinson.wang
	 */
	/*
	 * @SuppressWarnings("unchecked") public T getByCriteria(DetachedCriteria
	 * criteria) { T entity = (T) hibernateTemplate.findByCriteria(criteria)
	 * 
	 * if (entity == null) { log.warn("Uh oh, '" + this.persistentClass +
	 * "' object with id '" + id + "' not found..."); throw new
	 * ObjectRetrievalFailureException(this.persistentClass, id); }
	 * 
	 * return entity; }
	 */
	/**
	 * @author robinson.wang add manually,pass a string hql
	 * @param queryHQl
	 */
	public int updateEntity(String queryHQl) {

		Session session = sessionFactory.getCurrentSession();

		// Transaction trans =session.beginTransaction();

		Query query = session.createQuery(queryHQl);
		return query.executeUpdate();

		// trans.commit();

	}

	public int updateEntity(String queryHQl, Map<String, Object> parameters) {
		try{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryHQl);
		int i=0;
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(i, entry.getValue());
			i++;
		}

		return query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;

	}
	
	
	
	
	

	public List<T> getByHQL(String queryHQl, Map<String, Object> parameters, int firstResult, int maxResults) {

		return getTsData(queryHQl, parameters, firstResult, maxResults);

	}
	
	/**
	 * @author robinson.wang
	 * @param queryHQl
	 * @param parameters
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getByHQLReturnBaseVO(String queryHQl, Map<String, Object> parameters, int firstResult, int maxResults) {

		return getTsData(queryHQl, parameters, firstResult, maxResults);

	}

	private List<T> getTsData(String queryHQl, Map<String, Object> parameters, int firstResult, int maxResults) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(queryHQl);

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.list();
	}


	public void insert(String sql,Map<String,Object> parameters) {
		Session session = getSessionFactory().getCurrentSession();
		 SQLQuery query =session.createSQLQuery(sql);

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		 query.executeUpdate();
	}
	public void saveOrUpdateAll(Collection<T> entities){
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}
	
	
	// add by Mcihelle begin ----

	public T saveRmUserFrushHibernate(T object) {
		T ob = (T) hibernateTemplate.merge(object);
		hibernateTemplate.flush();
		return ob;
	}

	// add by Mcihelle begin ----

}
