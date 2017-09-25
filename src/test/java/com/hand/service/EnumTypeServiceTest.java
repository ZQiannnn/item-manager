package com.hand.service;

import com.alibaba.fastjson.JSON;
import com.hand.BaseTransactionalJunit4Test;
import com.hand.models.EnumType;
import com.hand.models.EnumValue;
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
public class EnumTypeServiceTest extends BaseTransactionalJunit4Test {


    @Autowired
    private IBaseTypeService<EnumType> enumTypeService;

    @Autowired
    private IBaseTypeService<EnumValue> enumValueService;

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
        element.set(map.get("enumtypes"));
    }


    @Test
    public void testResolveEnumType(){

        //插入枚举
        List<EnumType> enumtype = enumTypeService.resolveElementType(element.get(), extension.get(), "enumtype", true);
        LOG.info(JSON.toJSONString(enumtype));
        LOG.info("保存枚举的数量："+enumtype.size());
        final int[] sum = {0};
        enumtype.forEach(type-> sum[0] +=type.getValues().size());
        LOG.info("保存枚举值数量："+sum[0] );

        //
        LOG.info(JSON.toJSONString(enumTypeService.select(new EnumType())));

        int typeCount = enumTypeService.selectCount(new EnumType());
        LOG.info("查询枚举的长度："+ typeCount);
        int valueCount = enumValueService.selectCount(new EnumValue());
        LOG.info("查询枚举值的长度："+ valueCount);
        Assert.assertTrue(typeCount==enumtype.size());
        Assert.assertTrue(valueCount==sum[0]);

    }


}
