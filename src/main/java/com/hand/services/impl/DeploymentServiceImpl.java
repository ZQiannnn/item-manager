package com.hand.services.impl;

import com.hand.daos.IDeploymentDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.Deployment;
import com.hand.services.IDeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title TableServiceImpl
 * @Description Hybirs 处理deployment的实现类
 * @Author ZQian
 * @date: 2017/8/7 下午3:59
 */
@Service
public class DeploymentServiceImpl extends BaseTypeServiceImpl<Deployment> implements IDeploymentService {

    @Autowired
    private IDeploymentDao tableDao;

    @Override
    public IBaseMapper<Deployment> getBaseTypeDao() {
        return tableDao;
    }
}
