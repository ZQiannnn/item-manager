package com.hand.services.impl;

import com.hand.daos.IConfigurationDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.Configuration;
import com.hand.services.IConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title ConfigurationServiceImpl
 * @Description 系统配置实现类
 * @Author ZQian
 * @date: 2017/8/8 下午7:52
 */
@Service
public class ConfigurationServiceImpl  extends BaseServiceImpl<Configuration> implements IConfigurationService{

    @Autowired
    private IConfigurationDao configurationDao;

    @Override
    public IBaseMapper<Configuration> getBaseTypeDao() {
        return configurationDao;
    }
}
