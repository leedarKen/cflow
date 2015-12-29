package com.csoft.resource.cflow.dao;


import com.csoft.resource.cflow.BaseJunit4Test;
import com.csoft.resource.cflow.pojo.Category;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class CategoryDaoTest extends BaseJunit4Test {

    @Resource
    private CategoryDao categoryDao ;

    @Test
    @Transactional
    @Rollback(false)
    public void getCategoryWithDetailType(){
        Category travelAmountCatgory = new Category() ;
        travelAmountCatgory.setCategory("TravelAmount");
        travelAmountCatgory.setDetailType("CNY");
        travelAmountCatgory.setType("Currency");
        travelAmountCatgory.setValue("4000");
        travelAmountCatgory.setId(1);

        Map<String,String> parameters = new HashMap<String, String>() ;
        parameters.put("category", "TravelAmount") ;
        parameters.put("type", "Currency") ;
        parameters.put("detailType", "CNY") ;

        Category daoResult = categoryDao.getCategory(parameters) ;

        Assert.assertTrue(travelAmountCatgory.equals(daoResult));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getCategory(){

        Category travelAmountCatgory = new Category() ;
        travelAmountCatgory.setCategory("TravelAmount");
        travelAmountCatgory.setType("Currency");

        Map<String,String> parameters = new HashMap<String, String>() ;
        parameters.put("category", "TravelAmount") ;
        parameters.put("type", "Currency") ;

        Category daoResult = categoryDao.getCategory(parameters) ;

        Assert.assertEquals(daoResult.getCategory(), travelAmountCatgory.getCategory());
        Assert.assertEquals(daoResult.getType(), travelAmountCatgory.getType());
    }
}
