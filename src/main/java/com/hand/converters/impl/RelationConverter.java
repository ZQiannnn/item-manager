package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.models.Deployment;
import com.hand.models.Extension;
import com.hand.models.Relation;
import com.hand.models.RelationValue;
import com.hand.services.IBaseTypeService;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title RelationConverter
 * @Description Relation的转换器
 * @Author ZQian
 * @date: 2017/8/7 下午3:48
 */
@Component
public class RelationConverter implements Converter<Relation> {


    @Autowired
    private IBaseTypeService<Deployment> tableService;


    @Autowired
    private IBaseTypeService<RelationValue> relationValueService;

    @Override
    public Relation convert(Element element, Extension extension) {
        String code = "";
        try {
            Relation relation=new Relation();
            //code  一定有
            code = element.attribute("code").getValue();
            relation.setCode(code);

            //localized 一定有
            String localized = element.attribute("localized").getValue();
            relation.setLocalized(Boolean.parseBoolean(localized));

            //解析table   多对对关系中存在table
            if (element.element("deployment")!=null){
                List<Deployment> deployment = tableService.resolveElementType(element, extension, "deployment", insertTrigger);
                relation.setTable(deployment.iterator().next());
            }
            //解析source 一定有
            List<RelationValue> sourceElement = relationValueService.resolveElementType(element, extension, "sourceElement", insertTrigger);
            relation.setSource(sourceElement.iterator().next());

            //解析target  一定有
            List<RelationValue> targetElement = relationValueService.resolveElementType(element, extension, "targetElement", insertTrigger);
            relation.setTarget(targetElement.iterator().next());

            return relation;

        } catch (Exception e) {
            LOG.error("解析Relation出错",e);
            throw new UserException("Relation:"+code+"解析异常，请检查。");
        }
    }

    @Override
    public Element convert(Relation relation) {
        return null;
    }
}
