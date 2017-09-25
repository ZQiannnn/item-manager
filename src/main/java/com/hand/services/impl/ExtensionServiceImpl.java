package com.hand.services.impl;

import com.hand.daos.IExtensionDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.Extension;
import com.hand.services.IExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title ExtensionServiceImpl
 * @Description ExtensionService的实现类
 * @Author ZQian
 * @date: 2017/8/3 下午5:18
 */
@Service
public class ExtensionServiceImpl extends BaseServiceImpl<Extension> implements IExtensionService{

    @Autowired
    private IExtensionDao extensionDao;


    @Override
    public IBaseMapper<Extension> getBaseTypeDao() {
        return extensionDao;
    }
}
