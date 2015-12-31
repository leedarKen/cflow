package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.dao.CategoryDao;
import com.csoft.resource.cflow.pojo.Category;
import com.csoft.resource.cflow.service.inner.entry.service.CategoryManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("categoryManager")
public class CategoryManagerImpl
        extends GenericManagerImpl<Category, Integer>
        implements CategoryManager {

    static Logger logger = Logger.getLogger(CategoryManager.class) ;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    public CategoryManagerImpl(CategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }

    @Override
    public Category getCategory(String category, String type, String detailType) {
        Map<String,String> parameters = new HashMap<String, String>() ;
        parameters.put("category", category) ;
        parameters.put("type", type) ;
        parameters.put("detailType", detailType) ;
       return categoryDao.getCategory(parameters) ;
    }

    @Override
    public Category getCategory(String category, String type) {
        Map<String,String> parameters = new HashMap<String, String>() ;
        parameters.put("category", category) ;
        parameters.put("type", type) ;
        return categoryDao.getCategory(parameters) ;
    }
}
