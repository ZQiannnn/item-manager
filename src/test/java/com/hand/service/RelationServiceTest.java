package com.hand.service;

import com.alibaba.fastjson.JSON;
import com.hand.BaseTransactionalJunit4Test;
import com.hand.models.*;
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
public class RelationServiceTest extends BaseTransactionalJunit4Test{


    @Autowired
    private IBaseTypeService<Relation> relationService;


    @Autowired
    private IExtensionService extensionService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private IBaseTypeService<Deployment> tableService;

    @Autowired
    private IBaseTypeService<RelationValue> relationValueService;

    @Autowired
    private IBaseTypeService<Modifier> modifierService;



    private static ThreadLocal<Extension> extension=new ThreadLocal<>();
    private static ThreadLocal<Element> element=new ThreadLocal<>();



    @Before
    public void before() throws IOException {
        Extension hepcore = extensionService.selectByPrimaryKey("hepcore");
        extension.set(hepcore);
        Map<String, Element> map = fileService.readExtension(hepcore);
        element.set(map.get("relations"));
    }


    @Test
    public void testResolveRelation(){

        //插入关系
        List<Relation> relations = relationService.resolveElementType(element.get(), extension.get(), "relation", true);
        LOG.info(JSON.toJSONString(relations));
        LOG.info("保存关系的数量："+relations.size());
        int tableCount=0;
        int modifierCount=0;
        int valueCount=0;
        for (Relation relation : relations) {
            if (relation.getTable()!=null)tableCount++;
            if (relation.getSource()!=null){
                valueCount++;
                modifierCount++;
            }
            if (relation.getTarget()!=null){
                valueCount++;
                modifierCount++;
            }

        }
        LOG.info("保存table的数量："+tableCount);
        LOG.info("保存modifier的数量："+modifierCount);
        LOG.info("保存value的数量："+valueCount);
        //
        int typeCount = relationService.selectCount(new Relation());
        LOG.info("查询关系的长度："+ typeCount);
        LOG.info("查询table的数量："+tableService.selectCount(null));
        LOG.info("查询modifier的数量："+modifierService.selectCount(null));
        LOG.info("查询value的数量："+relationValueService.selectCount(null));
    }
}
