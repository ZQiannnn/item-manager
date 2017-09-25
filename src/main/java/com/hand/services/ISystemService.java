package com.hand.services;

import java.io.IOException;

/**
 * @Title ISystemService
 * @Description 系统接口
 * @Author ZQian
 * @date: 2017/8/8 下午7:35
 */
public interface ISystemService {
    /**
     * 初始化Item-Manager系统
     */
    void init() throws IOException;

    /**
     * 获取当前执行状态
     * @return
     */
    String getStatus();
}
