package com.hand.service;

import com.hand.BaseTransactionalJunit4Test;
import com.hand.models.Extension;
import com.hand.models.MapType;
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
public class MapTypeServiceTest extends BaseTransactionalJunit4Test {


    @Autowired
    private IBaseTypeService<MapType> mapTypeService;

    @Autowired
    private IExtensionService extensionService;

    @Autowired
    private IFileService fileService;


    private static ThreadLocal<Extension> extension=new ThreadLocal<>();
    private static ThreadLocal<Element> element=new ThreadLocal<>();



    @Before
    public void before() throws IOException {
        Extension catalog = extensionService.selectByPrimaryKey("catalog");
        extension.set(catalog);
        Map<String, Element> map = fileService.readExtension(catalog);
        element.set(map.get("maptypes"));
    }


    @Test
    public void testResolveMapType(){
        //已经测试通过
        List<MapType> collectiontype = mapTypeService.resolveElementType(element.get(), extension.get(), "maptype", true);
        LOG.info("保存的数量："+collectiontype.size());
        List<MapType> select = mapTypeService.select(new MapType());
        LOG.info("查询的数量："+select.size());
        Assert.assertTrue(select.size()==collectiontype.size());
    }


}
