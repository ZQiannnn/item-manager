package com.hand.services;

import com.hand.datasource.DataSource;
import com.hand.models.Extension;
import org.dom4j.Element;

import java.util.List;

/**
 * @Title BaseModelService
 * @Description 对所有需要解析type类Service的接口
 * @Author ZQian
 * @date: 2017/8/5 上午11:11
 */
@DataSource("testDataSource")
public interface IBaseTypeService<T> extends IBaseService<T> {

    /**
     * 把dom4j的element节点解析成一个个的Type实体
     * @param element dom4j节点
     * @param extension 的extension
     * @param nodename 要解析的nodeName
     * @param insert 是否直接插入
     */
    List<T> resolveElementType(Element element, Extension extension, String nodename,Boolean insert);

}
