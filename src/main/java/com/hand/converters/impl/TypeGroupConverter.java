package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.models.Extension;
import com.hand.models.TypeGroup;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @Title TypeGroupConverter
 * @Description TypeGroup的转换器
 * @Author ZQian
 * @date: 2017/8/8 上午9:55
 */
@Component
public class TypeGroupConverter implements Converter<TypeGroup> {


    @Override
    public TypeGroup convert(Element element, Extension extension) {
        String name = "";
        try {
            TypeGroup group=new TypeGroup();
            //name  一定有
            name = element.attribute("name").getValue();
            group.setCode(name);
            return group;

        } catch (Exception e) {
            LOG.error("解析TypeGroup出错",e);
            throw new UserException("TypeGroup:"+name+"解析异常，请检查。");
        }
    }

    @Override
    public Element convert(TypeGroup group) {
        return null;
    }
}
