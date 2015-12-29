package com.csoft.resource.cflow.service.inner.entry.service;

import com.csoft.resource.cflow.pojo.Category;


public interface CategoryManager extends GenericManager<Category, Integer> {

    Category getCategory(String category, String type, String detailType);

    Category getCategory(String category, String type);
}
