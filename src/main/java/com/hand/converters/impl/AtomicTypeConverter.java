package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.models.AtomicType;
import com.hand.models.Extension;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @Title ItemtypeConverter
 * @Description AtomicType的转换器
 * @Author ZQian
 * @date: 2017/8/5 下午1:34
 */
@Component
public class AtomicTypeConverter implements Converter<AtomicType> {



    @Override
    public AtomicType convert(Element element, Extension extension) {
        String code = "";
        try {
            AtomicType atomicType=new AtomicType();
            //code  一定有
            code = element.attribute("class").getValue();
            atomicType.setCode(code);


            //extends 可以为空  为空默认GenericItem
            Attribute extendsAttr = element.attribute("extends");
            if (attrIsEmpty(extendsAttr)){
                atomicType.setExtend("java.lang.Object");
            }else {
                atomicType.setExtend(extendsAttr.getValue());
            }


            return atomicType;

        } catch (Exception e) {
            LOG.error("解析AtomicType出错",e);
            throw new UserException("AtomicType:"+code+"解析异常，请检查。");
        }
    }

    @Override
    public Element convert(AtomicType atomicType) {
        return null;
    }
}
