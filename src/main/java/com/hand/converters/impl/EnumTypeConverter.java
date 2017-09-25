package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.models.EnumType;
import com.hand.models.EnumValue;
import com.hand.models.Extension;
import com.hand.services.IBaseTypeService;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title EnumTypeConverter
 * @Description Enumtype的转换器
 * @Author ZQian
 * @date: 2017/8/5 下午1:58
 */
@Component
public class EnumTypeConverter implements Converter<EnumType> {


    @Autowired
    private IBaseTypeService<EnumValue> enumValueService;


    @Override
    public EnumType convert(Element element,Extension extension) {
        String code = "";
        try {
            EnumType enumType = new EnumType();
            //code
            code = element.attribute("code").getValue();
            enumType.setCode(code);


            // dynamic 是否可以动态添加 非必输  默认false
            Attribute dynamicAttr = element.attribute("dynamic");
            if (attrIsEmpty(dynamicAttr)) {
                enumType.setDynamic(false);
            } else {
                enumType.setDynamic(Boolean.valueOf(dynamicAttr.getValue()));
            }

            // description 描述 非必输
            Element descElement = element.element("description");
            if (!elementIsEmpty(descElement)) {
                enumType.setDescription(descElement.getTextTrim());
            }

            //autocreate
            enumType.setAutocreate(isAutocreate(element));


            //值解析
            List<EnumValue> enumValues = enumValueService.resolveElementType(element, extension, "value", insertTrigger);
            enumType.setValues(enumValues);


            return enumType;

        } catch (Exception e) {
            LOG.error("解析EnumType出错", e);
            throw new UserException("enumtype:" + code + "解析异常，请检查。");
        }
    }

    @Override
    public Element convert(EnumType enumType) {
        return null;
    }
}
