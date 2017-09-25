package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.enums.PersistenceType;
import com.hand.models.Extension;
import com.hand.models.Persistence;
import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Title PersistenceConverter
 * @Description Persistence的转换器
 * @Author ZQian
 * @date: 2017/8/8 下午3:34
 */
@Component
public class PersistenceConverter implements Converter<Persistence> {




    @Override
    public Persistence convert(Element element, Extension extension) {
        String qualifier = "";
        String type = "";
        try {
            Persistence persistence=new Persistence();
            //qualifier  业务约定必输
            qualifier = element.getParent().attribute("qualifier").getValue();
            persistence.setQualifier(qualifier);

            //type  一定有

            //取父itemtype或父relation
            Optional any = element.selectNodes("../../.. | ../..").stream().filter(e ->
                    ((Element) e).getName().equals("itemtype") || ((Element) e).getName().equals("relation")).findAny();
            type = ((Element) any.get()).attribute("code").getValue();
            persistence.setTypeCode(type);


            //propertyType
            Attribute typeAttr = element.attribute("type");
            if (attrIsEmpty(typeAttr)){
                persistence.setType(PersistenceType.property);
            }else {
                persistence.setType(PersistenceType.valueOf(typeAttr.getValue()));
            }

            //是否为长字符串
            persistence.setLongString(CollectionUtils.isNotEmpty(element.selectNodes("columntype")));

            //attributeHandler dynamic的时候有
            persistence.setAttributeHandler(element.attributeValue("attributeHandler"));



            return persistence;

        } catch (Exception e) {
            LOG.error("解析Persistence出错",e);
            throw new UserException("Persistence:"+type+"-"+qualifier+"解析异常，请检查。");
        }
    }

    @Override
    public Element convert(Persistence persistence) {
        return null;
    }
}
