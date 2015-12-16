package com.csoft.resource.cflow.service.inner.entry.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;

import com.csoft.resource.cflow.dao.GenericDao;
import com.csoft.resource.cflow.service.inner.entry.service.GenericManager;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *     &lt;bean id="userManager" class="com.csoft.resource.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.csoft.resource.dao.hibernate.GenericDaoHibernate"&gt;
 *                 &lt;constructor-arg value="com.csoft.resource.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p/>
 * <p>If you're using iBATIS instead of Hibernate, use:
 * <pre>
 *     &lt;bean id="userManager" class="com.csoft.resource.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.csoft.resource.dao.ibatis.GenericDaoiBatis"&gt;
 *                 &lt;constructor-arg value="com.csoft.resource.model.User"/&gt;
 *                 &lt;property name="dataSource" ref="dataSource"/&gt;
 *                 &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * GenericDao instance, set by constructor of child classes
	 */
	protected GenericDao<T, PK> dao;

	public GenericManagerImpl() {
	}

	public GenericManagerImpl(GenericDao<T, PK> genericDao) {
		this.dao = genericDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> getAll() {
		return dao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public T get(PK id) {
		return dao.get(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean exists(PK id) {
		return dao.exists(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public T save(T object) {
		return dao.save(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		dao.remove(id);
	}


	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {


		List<T> results  = dao.findByCriteria(criteria, firstResult, maxResults);

		if (log.isDebugEnabled()) {
			log.debug("Number of results : " + results.size());
		}

		return results;
	}

	@Deprecated
	public List<T> getByHQL(String queryHQl, Map<String, Object> parameters, int firstResult, int maxResults) {
		return dao.getByHQL(queryHQl, parameters, firstResult, maxResults);
	}


	public List<Object> findBySQL(String sqlStr, Map<String, Object> parameters, int firstResult, int maxResults) {


/*        List<T> results = new ArrayList<T>();
        results=dao.findBySQL(sqlStr, firstResult, maxResults);

        if (log.isDebugEnabled()) {
            log.debug("Number of results : " + results.size());
        }
*/
		return dao.findBySQL(sqlStr, parameters, firstResult, maxResults);
	}

	public int updateEntity(String queryHQl) {
		return dao.updateEntity(queryHQl);

	}

	public int updateEntity(String queryHQl, Map<String, Object> parameters) {
		return dao.updateEntity(queryHQl, parameters);


	}

	public int updateEntity(Class clazz, Map<String, Object> parameters, Map<String, Object> conditionAnd) {

		StringBuilder queryHQL = new StringBuilder("Update ");
		queryHQL.append(clazz.getName());
		queryHQL.append(" set ");
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			queryHQL.append(entry.getKey() + "=?");
			queryHQL.append(" ,");
		}
		queryHQL.deleteCharAt(queryHQL.length() - 1);//delete the last ,
		queryHQL.append(" where ");
		for (Map.Entry<String, Object> entry : conditionAnd.entrySet()) {
			queryHQL.append(entry.getKey() + "=?");
			queryHQL.append(" and ");
			parameters.put(entry.getKey(), entry.getValue());
		}

		queryHQL.delete(queryHQL.length() - 5, queryHQL.length() - 1);//delete the last 'and '
		return dao.updateEntity(queryHQL.toString(), parameters);


	}

	@Override
	public void update(T object) {
		dao.update(object);

	}

	public void saveOrUpdateAll(Collection<T> entities) {
		dao.saveOrUpdateAll(entities);
	}


}
