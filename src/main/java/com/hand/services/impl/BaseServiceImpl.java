package com.hand.services.impl;

import com.hand.mapper.IBaseMapper;
import com.hand.services.IBaseService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @Title BaseServiceImpl
 * @Description 基础Service实现类
 * @Author ZQian
 * @date: 2017/8/8 下午7:46
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    /**
     * 插入数据的方法，针对可覆盖的type如enumtype和itemtype需要重写
     * @param ts
     */
    @Override
    public void insertList(List<T> ts) {
        if (CollectionUtils.isNotEmpty(ts))
        getBaseTypeDao().insertList(ts);
    }

    /**
     * 通用的条件查询查询方法
     * @param t
     * @return
     */
    @Override
    public List<T> select(T t) {
        return getBaseTypeDao().select(t);
    }

    /**
     * 通用的条件查询数量方法
     * @param t
     * @return
     */
    @Override
    public int selectCount(T t) {
        return getBaseTypeDao().selectCount(t);
    }

    /**
     * 条件查找单个
     * @param t
     * @return
     */
    @Override
    public T selectOne(T t) {
        return getBaseTypeDao().selectOne(t);
    }

    /**
     * 主键查找
     * @param key
     * @return
     */
    @Override
    public T selectByPrimaryKey(Object key) {
        return getBaseTypeDao().selectByPrimaryKey(key);
    }

    @Override
    public int insert(T t) {
        return getBaseTypeDao().insert(t);
    }

    /**
     * 需要实现类来提供dao层进行处理
     * @return
     */
    public abstract IBaseMapper<T> getBaseTypeDao();
}
