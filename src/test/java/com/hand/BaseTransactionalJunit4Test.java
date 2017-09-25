package com.hand;

import com.hand.services.IExtensionService;
import com.hand.services.IHybrisService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Title com.hand.BaseJunit4Test
 * @Description 单元测试基类
 * @Author ZQian
 * @date: 2017/8/2 上午11:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-global.xml"})
//------------如果加入以下代码，所有继承该类的测试类都会遵循该配置，也可以不加，在测试类的方法上///控制事务，参见下一个实例
//这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！
//这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时//指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！
//------------
public class BaseTransactionalJunit4Test extends AbstractTransactionalJUnit4SpringContextTests {

    protected static final Logger LOG= LoggerFactory.getLogger(BaseTransactionalJunit4Test.class);

    @Autowired
    private IHybrisService hybrisService;
    @Autowired
    private IExtensionService extensionService;

    @Before
    public void setUp() throws IOException {
        if (extensionService.selectCount(null)==0){
            hybrisService.scanHybrisExtensions("/Users/zhang/Work/WorkSpace/yth/hybris");
            extensionService.insertList(new ArrayList<>(hybrisService.getExtensions()));
        }
    }

}
