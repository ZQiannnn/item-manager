package com.hand.service;

import com.hand.BaseTransactionalJunit4Test;
import com.hand.models.CollectionType;
import com.hand.models.Extension;
import com.hand.services.IBaseTypeService;
import com.hand.services.IExtensionService;
import com.hand.services.IFileService;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Title CollectionTypeTest
 * @Description collectiontype的单元测试类
 * @Author ZQian
 * @date: 2017/8/5 上午9:30
 */
public class CollectionTypeServiceTest extends BaseTransactionalJunit4Test {


    @Autowired
    private IBaseTypeService<CollectionType> collectionService;

    @Autowired
    private IExtensionService extensionService;

    @Autowired
    private IFileService fileService;


    private static ThreadLocal<Extension> extension=new ThreadLocal<>();
    private static ThreadLocal<Element> element=new ThreadLocal<>();



    @Before
    public void before() throws IOException {
        Extension hepcore = extensionService.selectByPrimaryKey("hepcore");
        extension.set(hepcore);
        Map<String, Element> map = fileService.readExtension(hepcore);
        element.set(map.get("collectiontypes"));
    }


    @Test
    public void testResolveAtomicType(){
        //已经测试通过
        List<CollectionType> collectiontype = collectionService.resolveElementType(element.get(), extension.get(), "collectiontype", true);
        LOG.info("保存的数量："+collectiontype.size());
        List<CollectionType> select = collectionService.select(new CollectionType());
        LOG.info("查询的数量："+select.size());
        Assert.assertTrue(select.size()==collectiontype.size());
    }


}
