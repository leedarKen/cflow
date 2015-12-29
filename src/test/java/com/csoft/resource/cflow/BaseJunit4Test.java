package com.csoft.resource.cflow;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ({"classpath:config/spring/ApplicationContext-dao.xml",
                        "classpath:config/spring/ApplicationContext-service.xml",
                        "classpath:config/hibernate/hibernate.cfg.xml"}) //加载配置文件
public class BaseJunit4Test {
}
