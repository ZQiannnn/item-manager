package com.hand.services.impl;

import com.hand.daos.IEnumTypeDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.EnumType;
import com.hand.models.EnumValue;
import com.hand.services.IEnumTypeService;
import com.hand.services.IEnumValueService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title EnumTypeServiceImpl
 * @Description Hybris enumtype处理的实现类
 * @Author ZQian
 * @date: 2017/8/5 下午1:56
 */
@Service
public class EnumTypeServiceImpl extends BaseTypeServiceImpl<EnumType> implements IEnumTypeService {

    private static Logger LOG = LoggerFactory.getLogger(EnumTypeServiceImpl.class);

    @Autowired
    private IEnumTypeDao enumTypeDao;

    @Autowired
    private IEnumValueService enumValueService;

    @Override
    public IBaseMapper<EnumType> getBaseTypeDao() {
        return enumTypeDao;
    }

    @Override
    public void insertList(List<EnumType> enumType) {
        if (CollectionUtils.isEmpty(enumType)) return;
        List<EnumType> newType = new ArrayList<>();
        List<EnumValue> enumValues = new ArrayList<>();

        //先插入值  再插入枚举
        for (EnumType type : enumType) {
            //先查询
            if (enumTypeDao.selectByPrimaryKey(type.getCode()) != null) {
                if (type.getAutocreate())
                    enumTypeDao.updateByPrimaryKey(type);
            } else {
                newType.add(type);
            }
            if (CollectionUtils.isNotEmpty(type.getValues()))
                enumValues.addAll(type.getValues());
        }
        if (CollectionUtils.isNotEmpty(newType)) enumTypeDao.insertList(newType);
        //枚举插入完成后一次性插入所有值
        enumValueService.insertList(enumValues);
    }
}
