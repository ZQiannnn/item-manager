package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.models.Extension;
import com.hand.models.Deployment;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @Title TableConverter
 * @Description deployment 转换器
 * @Author ZQian
 * @date: 2017/8/7 下午4:01
 */
@Component
public class DeploymentConverter implements Converter<Deployment>{



    @Override
    public Deployment convert(Element element, Extension extension) {
        String name = "";
        try {
            Deployment table=new Deployment();
            //table  一定有
            name = element.attribute("table").getValue();
            table.setName(name);

            //code  一定有
            String code = element.attribute("typecode").getValue();
            table.setCode(code);

            //父type的code
            String parentCode = element.getParent().attribute("code").getValue();
            table.setTypeCode(parentCode);


            return table;

        } catch (Exception e) {
            LOG.error("解析Deployment出错",e);
            throw new UserException("Deployment:"+name+"解析异常，请检查。");
        }
    }

    @Override
    public Element convert(Deployment table) {
        return null;
    }
}
