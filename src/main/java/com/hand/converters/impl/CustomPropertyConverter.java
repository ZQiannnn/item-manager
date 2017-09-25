package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.models.CustomProperty;
import com.hand.models.Extension;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @Title CustomPropertyConverter
 * @Description CustomProperty的转换器
 * @Author ZQian
 * @date: 2017/8/8 上午11:27
 */
@Component
public class CustomPropertyConverter implements Converter<CustomProperty> {


    @Override
    public CustomProperty convert(Element element, Extension extension) {
        String name = "";
        try {
            CustomProperty property=new CustomProperty();
            //name  一定有
            name = element.attribute("name").getValue();
            property.setName(name);

            //value   业务约定必输
            property.setValue(element.elementText("value"));

            //父type的code
            String parentCode = element.getParent().getParent().attribute("code").getValue();
            property.setTypeCode(parentCode);

            return property;

        } catch (Exception e) {
            LOG.error("解析CustomProperty出错",e);
            throw new UserException("CustomProperty:"+name+"解析异常，请检查。");
        }
    }

    @Override
    public Element convert(CustomProperty customProperty) {
        return null;
    }
}
