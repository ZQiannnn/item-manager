package com.hand.services.impl;

import com.hand.daos.IPersistenceDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.Persistence;
import com.hand.services.IPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title PersistenServiceImpl
 * @Description Hybris Persistence的处理类
 * @Author ZQian
 * @date: 2017/8/8 下午3:32
 */
@Service
public class PersistenServiceImpl extends BaseTypeServiceImpl<Persistence> implements IPersistenceService {

    @Autowired
    private IPersistenceDao persistenceDao;

    @Override
    public IBaseMapper<Persistence> getBaseTypeDao() {
        return persistenceDao;
    }
}
