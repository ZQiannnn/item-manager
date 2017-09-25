package com.hand.services;

import com.hand.models.Extension;
import org.dom4j.Element;

import java.io.IOException;
import java.util.Map;

/**
 * @Title FileService
 * @Description 针对文件的一些操作
 * @Author ZQian
 * @date: 2017/8/2 下午6:04
 */
public interface IFileService {

    /**
     * 读取一个Extension的items.xml
     * @param extension
     * @return 返回dom4j的element列表
     */
    Map<String, Element> readExtension(Extension extension) throws IOException;

}
