package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.models.Extension;
import com.hand.models.Modifier;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Title ModifierConverter
 * @Description Hybris Modifier的转换器
 * @Author ZQian
 * @date: 2017/8/7 下午4:43
 */
@Component
public class ModifierConverter implements Converter<Modifier>{


    @Override
    public Modifier convert(Element element, Extension extension) {
        //type 和 qualifier 为父类型的 code和父字段的 qualifier 用来确定唯一性
        String type = "";
        String qualifier = "";
        try {
            Modifier modifier=new Modifier();
            //qualifier  业务约定必输
            qualifier = element.getParent().attributeValue("qualifier");
            modifier.setQualifier(qualifier==null?"":qualifier);

            //type  一定有

            //取父itemtype或父relation
            Optional any = element.selectNodes("../../.. | ../..").stream().filter(e ->
                    ((Element) e).getName().equals("itemtype") || ((Element) e).getName().equals("relation")).findAny();
            type = ((Element) any.get()).attribute("code").getValue();
            modifier.setTypeCode(type);

            //unique  非必输 默认为true
            Attribute uniqueAttr = element.attribute("unique");
            if (attrIsEmpty(uniqueAttr)) {
                modifier.setUnique(Boolean.FALSE);
            }else {
                modifier.setUnique(Boolean.valueOf(uniqueAttr.getValue()));
            }

            //read  非必输 默认为true
            Attribute readAttr = element.attribute("read");
            if (attrIsEmpty(readAttr)) {
                modifier.setRead(Boolean.TRUE);
            }else {
                modifier.setRead(Boolean.valueOf(readAttr.getValue()));
            }


            //write  非必输 默认为true
            Attribute writeAttr = element.attribute("write");
            if (attrIsEmpty(writeAttr)) {
                modifier.setWrite(Boolean.TRUE);
            }else {
                modifier.setWrite(Boolean.valueOf(writeAttr.getValue()));
            }

            //search  非必输 默认为true
            Attribute searchAttr = element.attribute("search");
            if (attrIsEmpty(searchAttr)) {
                modifier.setSearch(Boolean.TRUE);
            }else {
                modifier.setSearch(Boolean.valueOf(searchAttr.getValue()));
            }

            //optional  非必输 默认为true
            Attribute optionalAttr = element.attribute("optional");
            if (attrIsEmpty(optionalAttr)) {
                modifier.setOptional(Boolean.TRUE);
            }else {
                modifier.setOptional(Boolean.valueOf(optionalAttr.getValue()));
            }


            return modifier;

        } catch (Exception e) {
            LOG.error("解析Modifier出错",e);
            throw new UserException("Modifier:"+type+"-"+qualifier+"解析异常，请检查。");
        }
    }

    @Override
    public Element convert(Modifier modifier) {
        return null;
    }
}
