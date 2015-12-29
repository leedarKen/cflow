package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.dao.CategoryDao;
import com.csoft.resource.cflow.pojo.Category;
import com.csoft.resource.cflow.service.inner.entry.service.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("categoryManager")
public class CategoryManagerImpl
        extends GenericManagerImpl<Category, Integer>
        implements CategoryManager {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    public CategoryManagerImpl(CategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }

    @Override
    public Category getCategory(String category, String type, String detailType) {
       return categoryDao.getCategory(category, type, detailType) ;
    }

    @Override
    public Category getCategory(String category, String type) {
        return categoryDao.getCategory(category, type) ;
    }
}
