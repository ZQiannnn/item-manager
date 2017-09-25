package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.enums.CollectionEnum;
import com.hand.models.CollectionType;
import com.hand.models.Extension;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @Title CollectionTypeConvert
 * @Description Collectiontype的转换类
 * @Author ZQian
 * @date: 2017/8/3 下午8:16
 */
@Component
public class CollectionTypeConvert implements Converter<CollectionType> {


    @Override
    public CollectionType convert(Element element,Extension extension) {
        String code = "";
        try {
            CollectionType collectionType=new CollectionType();
            //code  一定有
            code = element.attribute("code").getValue();
            collectionType.setCode(code);

            //elementtype 一定有
            collectionType.setElementtypeCode(element.attribute("elementtype").getValue());

            //type为optional的，不输默认collection
            Attribute typeAttr = element.attribute("type");
            if (attrIsEmpty(typeAttr)){
                collectionType.setType(CollectionEnum.collection);
            }else{
                collectionType.setType(CollectionEnum.valueOf(typeAttr.getValue()));
            }



            return collectionType;

        } catch (Exception e) {
           LOG.error("解析CollectionType出错",e);
           throw new UserException("collectiontype:"+code+"解析异常，请检查。");
        }
    }

    @Override
    public Element convert(CollectionType collectionType) {
        return null;
    }
}
