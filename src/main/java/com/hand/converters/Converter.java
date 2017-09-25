package com.hand.converters;

import com.hand.models.Extension;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title Converter
 * @Description 基础的转换接口
 * @Author ZQian
 * @date: 2017/8/3 下午6:47
 */
public interface Converter<T> {

    Logger LOG = LoggerFactory.getLogger(Converter.class);

    //是否插入开关
    Boolean insertTrigger=false;

    /**
     * 从 element 转换到 model
     *
     * @param element 解析的dom元素
     * @param extension 当前所分析的extension
     * @return
     */
    T convert(Element element,Extension extension);

    /**
     * 批量 从 element 转换到 model
     *
     * @param elements  批量解析的dom元素
     * @param extension 当前所分析的extension
     * @return
     */
    default List<T> convertAll(List<Element> elements, Extension extension){
        List<T> result=new ArrayList<>();
        elements.forEach(element->result.add(convert(element,extension)));
        return result;
    }

    /**
     * 从 model 转换到 element
     *
     * @param t
     * @return
     */
    Element convert(T t);

    /**
     * 批量 从 model 转换到 element
     *
     * @param ts
     * @return
     */
    default List<Element> convertAll(List<T> ts){
        List<Element> result=new ArrayList<>();
        ts.forEach(t->result.add(convert(t)));
        return result;
    }

    /**
     * 判断是否在custom底下的
     *
     * @param extension 所在Extension
     * @return
     */
    default Boolean isCustom(Extension extension){
        //在custom底下
        if (extension.getPath().contains("custom")) {
            return true;
        }
        //不在custom文件夹底下
        return false;
    }

    /**
     * 判断是否是原生的
     *
     * @param extension 所在Extension
     * @return
     */
    default Boolean isOrigin(Element element,Extension extension){
        //在custom底下
        if (extension.getPath().contains("custom")){
            //判断generate属性，若为true或不存在则为新建的，否则为原生
            Attribute generate = element.attribute("generate");
            if (generate==null || generate.getValue().equals("true")){
                return false;
            }
        }
        //不在custom文件夹底下肯定是原生的
        return true;
    }

    /**
     * 判断是否autocreate
     *
     * @param element   dom元素
     * @return
     */
    default Boolean isAutocreate(Element element) {
        //判断generate属性，若为true或不存在则为新建的，否则为原生
        Attribute generate = element.attribute("autocreate");
        if (generate == null || generate.getValue().equals("true")) {
            return true;
        }
        //不在custom文件夹底下肯定是原生的
        return false;
    }

    /**
     * 判断attr是否为空
     * @param attr
     * @return
     */
    default Boolean attrIsEmpty(Attribute attr) {
        return attr == null || StringUtils.isEmpty(attr.getValue());
    }

    /**
     * 判断element是否为空
     * @param element
     * @return
     */
    default Boolean elementIsEmpty(Element element) {
        return element == null || StringUtils.isEmpty(element.getTextTrim());
    }


}
