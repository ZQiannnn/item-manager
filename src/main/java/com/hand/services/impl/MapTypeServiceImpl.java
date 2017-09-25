package com.hand.services.impl;

import com.hand.daos.IMapTypeDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.MapType;
import com.hand.services.IMapTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title AtomicTypeServiceImpl
 * @Description Hybris AtomicType的处理类
 * @Author ZQian
 * @date: 2017/8/9 上午10:59
 */
@Service
public class MapTypeServiceImpl extends BaseTypeServiceImpl<MapType> implements IMapTypeService {

    @Autowired
    private IMapTypeDao mapTypeDao;

    @Override
    public IBaseMapper<MapType> getBaseTypeDao() {
        return mapTypeDao;
    }
}
