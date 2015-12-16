package com.csoft.resource.cflow.service.inner.entry.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

/**
 * Generic Manager that talks to GenericDao to CRUD POJOs.
 *
 * <p>Extend this interface if you want typesafe (no casting necessary) managers
 * for your domain objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public interface GenericManager<T, PK extends Serializable> {
	 String successView="forward:/common/success.jsp";
     String errorView="forward:/common/error.jsp";
     String messageView="forward:/common/messages.jsp";
    /**
     * Generic method used to get all objects of a particular type. This
     * is the same as lookup up all rows in a table.
     * @return List of populated objects
     */
    List<T> getAll();

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * Checks for existence of an object of type T using the id arg.
     * @param id the identifier (primary key) of the object to get
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    /**
     * Generic method to save an object - handles both update and insert.
     * @param object the object to save
     * @return the updated object
     */
    T save(T object);
	void update(T object);
    /**
     * Generic method to delete an object based on class and id
     * @param id the identifier (primary key) of the object to remove
     */
    void remove(PK id);

    /**
     * Generic method to search for an object.
     * @param criteria the search term
     * @param firstResult type of class to search for.
     * @param maxResults type of class to search for.
     * @return a list of matched objects
     */

    
    
    List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults);


    int updateEntity(String queryHQl);
    int updateEntity(String queryHQl, Map<String, Object> parameters);
    int updateEntity(Class clazz, Map<String, Object> parameters, Map<String, Object> conditionAnd);
    List<T> getByHQL(String queryHQl, Map<String, Object> parameters, int firstResult, int maxResults);
    List<Object> findBySQL(String sqlStr, Map<String, Object> parameters, int firstResult, int maxResults);
    void saveOrUpdateAll(Collection<T> entities);
}
