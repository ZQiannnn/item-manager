package com.hand.service;

import com.alibaba.fastjson.JSON;
import com.hand.BaseTransactionalJunit4Test;
import com.hand.models.*;
import com.hand.services.IBaseTypeService;
import com.hand.services.IExtensionService;
import com.hand.services.IFileService;
import com.hand.services.IItemTypeService;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Title RelationServiceTest
 * @Description RelationService的测试
 * @Author ZQian
 * @date: 2017/8/7 下午3:41
 */
public class ItemTypeServiceTest extends BaseTransactionalJunit4Test{




    @Autowired
    private IExtensionService extensionService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private IItemTypeService itemTypeService;

    @Autowired
    private IBaseTypeService<Deployment> deploymentService;

    @Autowired
    private IBaseTypeService<CustomProperty> customPropertyService;

    @Autowired
    private IBaseTypeService<Field> attributeService;

    @Autowired
    private IBaseTypeService<Index> indexService;

    @Autowired
    private IBaseTypeService<Modifier> modifierService;

    @Autowired
    private IBaseTypeService<Persistence> persistenceService;





    private static ThreadLocal<Extension> extension=new ThreadLocal<>();
    private static ThreadLocal<Element> element=new ThreadLocal<>();



    @Before
    public void before() throws IOException {
        Extension hepcore = extensionService.selectByPrimaryKey("hepcore");
        extension.set(hepcore);
        Map<String, Element> map = fileService.readExtension(hepcore);
        element.set(map.get("itemtypes"));
    }


    @Test
    public void testResolveItemType(){

        //插入关系
        List<ItemType> itemTypes = itemTypeService.resolveElementType(element.get(), extension.get(), "typegroup/itemtype", true);
        LOG.info(JSON.toJSONString(itemTypes));
        //
        LOG.info("查询ItemType的长度："+ itemTypeService.selectCount(new ItemType()));
        LOG.info("查询Deployment的数量："+deploymentService.selectCount(null));
        LOG.info("查询CustomProperty的数量："+customPropertyService.selectCount(null));
        LOG.info("查询Attribute的数量："+attributeService.selectCount(null));
        LOG.info("查询Index的数量："+indexService.selectCount(null));
        LOG.info("查询Modifier的数量："+modifierService.selectCount(null));
        LOG.info("查询Persistence的数量："+persistenceService.selectCount(null));
    }
}
