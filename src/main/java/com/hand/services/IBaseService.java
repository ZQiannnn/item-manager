package com.hand.services;

import java.util.List;

/**
 * @Title BaseModelService
 * @Description 对所有需要解析type类Service的接口
 * @Author ZQian
 * @date: 2017/8/5 上午11:11
 */
public interface IBaseService<T> {


    /**
     * 插入列表
     * @param ts
     */
    void insertList(List<T> ts);

    /**
     * 单条插入
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * 条件查找
     * @param t
     * @return
     */
    List<T> select(T t);

    /**
     * 条件查找数量
     * @param t
     * @return
     */
    int selectCount(T t);


    /**
     * 条件查找单个
     * @param t
     * @return
     */
    T selectOne(T t);

    /**
     * 主键查找
     * @param key
     * @return
     */
    T selectByPrimaryKey(Object key);
}
