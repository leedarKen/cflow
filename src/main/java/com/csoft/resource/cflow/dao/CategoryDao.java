package com.csoft.resource.cflow.dao;

import com.csoft.resource.cflow.pojo.Category;

public interface CategoryDao extends GenericDao<Category,Integer>{
	Category getCategory(String category, String type, String detailType);

	Category getCategory(String category, String type);
}
