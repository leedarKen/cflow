package com.csoft.resource.cflow.dao;

import com.csoft.resource.cflow.pojo.Category;

import java.util.Map;

public interface CategoryDao extends GenericDao<Category, Integer> {

    Category getCategory(Map<String, String> parameters);
}
