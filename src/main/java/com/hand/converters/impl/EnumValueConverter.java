package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.models.EnumValue;
import com.hand.models.Extension;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @Title EnumValueConverter
 * @Description Hybris枚举值的转换器
 * @Author ZQian
 * @date: 2017/8/5 下午2:57
 */
@Component
public class EnumValueConverter implements Converter<EnumValue> {



    @Override
    public EnumValue convert(Element element,Extension extension) {
        String code = "";
        try {
            EnumValue enumValue = new EnumValue();
            //code
            code = element.attribute("code").getValue();
            enumValue.setCode(code);

            // description 描述 非必输
            Element descElement = element.element("description");
            if (!elementIsEmpty(descElement)) {
                enumValue.setDescription(descElement.getTextTrim());
            }

            //父枚举的code
            String enumCode = element.getParent().attribute("code").getValue();
            enumValue.setEnumCode(enumCode);

            return enumValue;

        } catch (Exception e) {
            LOG.error("解析EnumValue出错", e);
            throw new UserException("enumvalue:" + code + "解析异常，请检查。");
        }
    }

    @Override
    public Element convert(EnumValue enumValue) {
        return null;
    }
}
