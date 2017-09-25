package com.hand.services.impl;

import com.hand.daos.ICollecionTypeDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.CollectionType;
import com.hand.services.ICollectionTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title CollectionServiceImplI
 * @Description CollectionType处理的实现逻辑
 * @Author ZQian
 * @date: 2017/8/3 下午7:50*/
@Service
public class CollectionTypeServiceImpl extends BaseTypeServiceImpl<CollectionType> implements ICollectionTypeService {

    private static final Logger LOG = LoggerFactory.getLogger(ICollectionTypeService.class);


    @Autowired
    private ICollecionTypeDao collecionTypeDao;

    @Override
    public IBaseMapper<CollectionType> getBaseTypeDao() {
        return collecionTypeDao;
    }
}
