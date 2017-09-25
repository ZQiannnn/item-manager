package com.hand.services;

import com.hand.models.Extension;

import java.io.IOException;
import java.util.Set;

/**
 * @Title IHybrisService
 * @Description 与hybris相关的service
 * @Author ZQian
 * @date: 2017/8/2 下午7:44
 */
public interface IHybrisService {

    /**
     * 获取目前所有的Extensions
     * @return
     */
    Set<Extension> getExtensions();

    /**
     * 提供Hybris路径扫描Hybris Extension到系统中
     * @param path
     * @throws IOException
     */
    void scanHybrisExtensions(String path) throws IOException;
}
