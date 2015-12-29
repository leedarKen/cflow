package com.csoft.resource.cflow.dao.impl;

import com.csoft.resource.cflow.dao.CategoryDao;
import com.csoft.resource.cflow.dao.GenericDao;
import com.csoft.resource.cflow.pojo.Category;
import com.csoft.resource.cflow.pojo.InstanceTask;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryDao")
public class CategoryDaoHibernate
        extends GenericDaoHibernate<Category, Integer> implements CategoryDao {

    public CategoryDaoHibernate() {
        super(Category.class);
    }

    @Override
    public Category getCategory(String category, String type, String detailType) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria cri = session.createCriteria(Category.class);
        cri.add(Restrictions.eq("category", category));
        cri.add(Restrictions.eq("type", type));
        cri.add(Restrictions.eq("detailType", detailType));
        List<Category> categoryList = cri.list();

        if (categoryList != null && !categoryList.isEmpty()) {
            return categoryList.get(0);
        }

        return null;
    }

    @Override
    public Category getCategory(String category, String type) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria cri = session.createCriteria(Category.class);
        cri.add(Restrictions.eq("category", category));
        cri.add(Restrictions.eq("type", type));
        List<Category> categoryList = cri.list();

        if (categoryList != null && !categoryList.isEmpty()) {
            return categoryList.get(0);
        }

        return null;
    }
}
