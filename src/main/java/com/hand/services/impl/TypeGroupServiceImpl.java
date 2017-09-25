package com.hand.services.impl;

import com.hand.daos.ITypeGroupDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.TypeGroup;
import com.hand.services.ITypeGroupService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title TypeGroupServiceImpl
 * @Description Type Group的处理实现类
 * @Author ZQian
 * @date: 2017/8/8 上午9:57
 */
@Service
public class TypeGroupServiceImpl extends BaseTypeServiceImpl<TypeGroup> implements ITypeGroupService{

    @Autowired
    private ITypeGroupDao typeGroupDao;

    @Override
    public IBaseMapper<TypeGroup> getBaseTypeDao() {
        return typeGroupDao;
    }

    @Override
    public void insertList(List<TypeGroup> typeGroups) {
        if (CollectionUtils.isEmpty(typeGroups))return;

        List<TypeGroup> newGroup=new ArrayList<>();

        //可能有重复值，忽略
        for (TypeGroup type : typeGroups) {
            //先查询
            if (typeGroupDao.selectByPrimaryKey(type.getCode())==null) {
                newGroup.add(type);
            }
        }
        super.insertList(newGroup);
    }
}
