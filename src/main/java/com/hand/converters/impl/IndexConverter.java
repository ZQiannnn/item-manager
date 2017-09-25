package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.models.Extension;
import com.hand.models.Index;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title IndexConverter
 * @Description Hybris Index的转换器
 * @Author ZQian
 * @date: 2017/8/8 下午3:55
 */
@Component
public class IndexConverter implements Converter<Index> {


    @Override
    public Index convert(Element element, Extension extension) {
        String name = "";
        try {
            Index index=new Index();
            //name  一定有
            name = element.attribute("name").getValue();
            index.setName(name);

            //父type的code
            String typecode = element.getParent().getParent().attribute("code").getValue();
            index.setTypeCode(typecode);

            //unique 可以为空 默认为false
            Attribute uniqueAttr = element.attribute("unique");
            if (attrIsEmpty(uniqueAttr)) {
                index.setUnique(Boolean.FALSE);
            }else {
                index.setUnique(Boolean.valueOf(uniqueAttr.getValue()));
            }

            //keys
            List<String> keys=new ArrayList<>();
            List list = element.selectNodes("./key");
            list.forEach(node-> keys.add(((Element) node).attribute("attribute").getValue()));
            index.setKeys(keys);
            return index;

        } catch (Exception e) {
            LOG.error("解析Index出错",e);
            throw new UserException("Index:"+name+"解析异常，请检查。");
        }
    }

    @Override
    public Element convert(Index index) {
        return null;
    }
}
