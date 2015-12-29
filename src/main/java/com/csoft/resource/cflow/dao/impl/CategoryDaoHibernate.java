package com.csoft.resource.cflow.dao.impl;

import com.csoft.resource.cflow.dao.CategoryDao;
import com.csoft.resource.cflow.dao.GenericDao;
import com.csoft.resource.cflow.pojo.Category;
import com.csoft.resource.cflow.pojo.InstanceTask;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("categoryDao")
public class CategoryDaoHibernate
        extends GenericDaoHibernate<Category, Integer> implements CategoryDao {

    Logger logger = Logger.getLogger(CategoryDaoHibernate.class);

    public CategoryDaoHibernate() {
        super(Category.class);
    }

    @Override
    public Category getCategory(Map<String, String> parameters) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria cri = session.createCriteria(Category.class);
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            cri.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }

        List<Category> categoryList = cri.list();

        if (categoryList != null && !categoryList.isEmpty()) {
            logger.info("getCategory method end: category.id=" + categoryList.get(0).getId());
            return categoryList.get(0);
        }
        logger.info("getCategory method end: no Category found");
        return null;
    }
}
