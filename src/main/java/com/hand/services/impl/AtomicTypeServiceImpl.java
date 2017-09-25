package com.hand.services.impl;

import com.hand.daos.IAtomicTypeDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.AtomicType;
import com.hand.services.IAtomicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title AtomicTypeServiceImpl
 * @Description Hybris AtomicType的处理类
 * @Author ZQian
 * @date: 2017/8/9 上午10:59
 */
@Service
public class AtomicTypeServiceImpl extends BaseTypeServiceImpl<AtomicType> implements IAtomicTypeService {

    @Autowired
    private IAtomicTypeDao atomicTypeDao;

    @Override
    public IBaseMapper<AtomicType> getBaseTypeDao() {
        return atomicTypeDao;
    }
}
