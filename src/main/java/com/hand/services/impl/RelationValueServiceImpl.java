package com.hand.services.impl;

import com.hand.daos.IRelationValueDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.RelationValue;
import com.hand.services.IRelationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title RelationValueServiceImpl
 * @Description Relation的一端的处理实现类
 * @Author ZQian
 * @date: 2017/8/7 下午4:11
 */
@Service
public class RelationValueServiceImpl extends BaseTypeServiceImpl<RelationValue> implements IRelationValueService {


    @Autowired
    private IRelationValueDao relationValueDao;

    @Override
    public IBaseMapper<RelationValue> getBaseTypeDao() {
        return relationValueDao;
    }
}
