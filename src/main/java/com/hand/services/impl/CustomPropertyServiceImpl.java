package com.hand.services.impl;

import com.hand.daos.ICustomPropertyDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.CustomProperty;
import com.hand.services.ICustomPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title CustomPropertyServiceImpl
 * @Description Hybris type的custom-properties的处理类
 * @Author ZQian
 * @date: 2017/8/8 上午11:26
 */
@Service
public class CustomPropertyServiceImpl extends BaseTypeServiceImpl<CustomProperty> implements ICustomPropertyService {

    @Autowired
    private ICustomPropertyDao customPropertyDao;

    @Override
    public IBaseMapper<CustomProperty> getBaseTypeDao() {
        return customPropertyDao;
    }
}
