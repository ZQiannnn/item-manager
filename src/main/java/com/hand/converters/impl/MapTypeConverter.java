package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.models.Extension;
import com.hand.models.MapType;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @Title ItemtypeConverter
 * @Description MapType的转换器
 * @Author ZQian
 * @date: 2017/8/5 下午1:34
 */
@Component
public class MapTypeConverter implements Converter<MapType> {



    @Override
    public MapType convert(Element element, Extension extension) {
        String code = "";
        try {
            MapType mapType=new MapType();
            //code  一定有
            code = element.attribute("code").getValue();
            mapType.setCode(code);


            //argumenttype-key 一定有
            String keyType = element.attribute("argumenttype").getValue();
            mapType.setKeyType(keyType);

            //returntype-value 一定有
            String valueType = element.attribute("returntype").getValue();
            mapType.setValueType(valueType);

            return mapType;

        } catch (Exception e) {
            LOG.error("解析AtomicType出错",e);
            throw new UserException("AtomicType:"+code+"解析异常，请检查。");
        }
    }

    @Override
    public Element convert(MapType mapType) {
        return null;
    }
}
