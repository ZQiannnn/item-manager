package com.hand.service;

import com.hand.BaseTransactionalJunit4Test;
import com.hand.models.*;
import com.hand.services.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Title HybrisServiceTest
 * @Description hybrisservice单元测试类
 * @Author ZQian
 * @date: 2017/8/2 下午7:49
 */
public class SystemServiceTest extends BaseTransactionalJunit4Test {




    @Autowired
    private ISystemService systemService;

    @Autowired
    private IConfigurationService configurationService;


    @Autowired
    private ICollectionTypeService collectionTypeService;

    @Autowired
    private IEnumTypeService enumTypeService;


    @Autowired
    private IItemTypeService itemTypeService;
    @Autowired
    private IRelationService relationService;

    @Autowired
    private IAtomicTypeService atomicTypeService;

    @Autowired
    private IMapTypeService mapTypeService;

    @Before
    public void before(){
        Configuration configuration = new Configuration();
        configuration.setLocation("/Users/zhang/Work/WorkSpace/yth/hybris");
        configurationService.insert(configuration);
    }

    @Test
    public void testInit() throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(()-> LOG.info(systemService.getStatus()+"\n"),2,2, TimeUnit.SECONDS);
        try {
            systemService.init();
        } catch (Exception e) {
            LOG.info(systemService.getStatus()+"\n");
           throw e;
        }
        LOG.info("导入 AtomicType 总数："+atomicTypeService.selectCount(new AtomicType()));
        LOG.info("导入 MapType 总数："+mapTypeService.selectCount(new MapType()));
        LOG.info("导入 CollectionType 总数："+collectionTypeService.selectCount(new CollectionType()));
        LOG.info("导入 EnumType 总数："+enumTypeService.selectCount(new EnumType()));
        LOG.info("导入 Relation 总数："+relationService.selectCount(new Relation()));
        LOG.info("导入 ItemType 总数："+itemTypeService.selectCount(new ItemType()));


    }



}
