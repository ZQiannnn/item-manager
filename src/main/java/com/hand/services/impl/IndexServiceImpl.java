package com.hand.services.impl;

import com.hand.daos.IIndexDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.Index;
import com.hand.services.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title IndexServiceImpl
 * @Description Hybris Index处理实现类
 * @Author ZQian
 * @date: 2017/8/8 下午3:55
 */
@Service
public class IndexServiceImpl extends BaseTypeServiceImpl<Index> implements IIndexService {

    @Autowired
    private IIndexDao indexDao;

    @Override
    public IBaseMapper<Index> getBaseTypeDao() {
        return indexDao;
    }
}
