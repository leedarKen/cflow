package com.csoft.resource.cflow.service;

import com.csoft.resource.cflow.service.Rmservice;
import com.csoft.resource.cflow.service.impl.RmServiceImpl;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * RmServiceImpl Tester.
 *
 * @author <Authors name>
 * @since <pre>ʮһ�� 20, 2015</pre>
 * @version 1.0
 */
public class TestRmServiceImpl {

    @Autowired
    private Rmservice rmservice ;

    @Before
    public void before() throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:config/spring/Application-service-scan.xml" ,
                        "classpath:config/spring/ApplicationContext-dao.xml" ,
                        "classpath:config/spring/ApplicationContext-service.xml" ,
                        "classpath:config/spring/ApplicationContext-transaction.xml" ,
                        "classpath:config/mybatis/SqlMapConfig.xml"});
        rmservice = (Rmservice) context.getBean("rmservice");
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: selectByPrimaryKey(Integer id)
     *
     */
    @Test
    public void testSelectByPrimaryKey() throws Exception {
        com.csoft.resource.cflow.pojo.RmService service = rmservice.selectByPrimaryKey(1) ;
        assertEquals(1,service.getId().intValue()) ;
        //assertEquals(2,2);
    }


}
