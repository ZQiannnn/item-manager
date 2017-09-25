package com.hand.mapper;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * @Title IBaseMapper
 * @Description 扩展了批量插入的basemapper
 * @Author ZQian
 * @date: 2017/8/5 下午3:35
 */
public interface IBaseMapper<T> extends BaseMapper<T>,IInsertListMapper<T> {
}
