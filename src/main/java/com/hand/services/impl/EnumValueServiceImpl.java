package com.hand.services.impl;

import com.hand.daos.IEnumValueDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.EnumValue;
import com.hand.services.IEnumValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title EnumValueServiceImpl
 * @Description 枚举值处理的实现类
 * @Author ZQian
 * @date: 2017/8/7 下午1:50
 */
@Service
public class EnumValueServiceImpl extends BaseTypeServiceImpl<EnumValue> implements IEnumValueService {

    @Autowired
    private IEnumValueDao enumValueDao;

    @Override
    public IBaseMapper<EnumValue> getBaseTypeDao() {
        return enumValueDao;
    }
}
