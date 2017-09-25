package com.hand.services.impl;

import com.hand.converters.Converter;
import com.hand.models.AbstractType;
import com.hand.models.Extension;
import com.hand.models.Item;
import com.hand.services.IBaseTypeService;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title BaseTypeServiceImpl
 * @Description BaseTypeService的处理类 继承了BaseService的CRUD 拓展了转换功能
 * @Author ZQian
 * @date: 2017/8/5 上午11:29
 */
public abstract class BaseTypeServiceImpl<T extends Item> extends BaseServiceImpl<T> implements IBaseTypeService<T> {


    /**
     * 转换器
     */
    @Autowired
    private Converter<T> converter;


    @Override
    public List<T> resolveElementType(Element element, Extension extension, String nodename, Boolean insert) {
        //将需要选取的dom元素选出来
        List<T> ts = new ArrayList<>();
        if (element != null) {
            List list = element.selectNodes("./" + nodename);

            for (Object o : list) {
                //dom元素转换为model
                T t = converter.convert(((Element) o), extension);
                //设置通用参数
                t.setCustom(converter.isCustom(extension));
                //判断当前元素的generate值用来判断真实的extension
                Boolean generate = converter.isAutocreate((Element) o);
                //AbstractType 有 所在Extension 和 generate 字段
                if (generate && t instanceof AbstractType) {
                    ((AbstractType) t).setExtension(extension.getName());
                }

                //添加数据
                ts.add(t);
            }
            //选择保存方式，默认用dao插入list，可重写
            if (insert) {
                insertList(ts);
            }
        }
        return ts;
    }


    public Converter<T> getConverter() {
        return converter;
    }

    public void setConverter(Converter<T> converter) {
        this.converter = converter;
    }


}
