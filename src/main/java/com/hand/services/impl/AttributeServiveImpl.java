package com.hand.services.impl;

import com.hand.daos.IAttributeDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.Field;
import com.hand.services.IAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title AttributeServiveImpl
 * @Description Hybris type的attribute的处理类
 * @Author ZQian
 * @date: 2017/8/8 上午11:47
 */
@Service
public class AttributeServiveImpl extends BaseTypeServiceImpl<Field> implements IAttributeService {

    @Autowired
    private IAttributeDao attributeDao;

    @Override
    public IBaseMapper<Field> getBaseTypeDao() {
        return attributeDao;
    }
}
