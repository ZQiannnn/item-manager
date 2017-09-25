package com.hand.service;

import com.alibaba.fastjson.JSON;
import com.hand.BaseTransactionalJunit4Test;
import com.hand.models.Extension;
import com.hand.models.TypeGroup;
import com.hand.services.IBaseTypeService;
import com.hand.services.IExtensionService;
import com.hand.services.IFileService;
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
public class TypeGroupServiceTest extends BaseTransactionalJunit4Test{




    @Autowired
    private IExtensionService extensionService;

    @Autowired
    private IFileService fileService;


    @Autowired
    private IBaseTypeService<TypeGroup> typeGroupService;




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
        List<TypeGroup> groups = typeGroupService.resolveElementType(element.get(), extension.get(), "typegroup", true);
        LOG.info(JSON.toJSONString(groups));
        LOG.info("保存Group的数量："+groups.size());
        //
        int typeCount = typeGroupService.selectCount(new TypeGroup());
        LOG.info("查询Group的长度："+ typeCount);
    }
}
