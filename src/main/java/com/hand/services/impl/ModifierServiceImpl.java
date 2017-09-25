package com.hand.services.impl;

import com.hand.daos.IModifierDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.Modifier;
import com.hand.services.IModifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title ModifierServiceImpl
 * @Description Hybris Modifier处理的实现类
 * @Author ZQian
 * @date: 2017/8/7 下午4:42
 */
@Service
public class ModifierServiceImpl extends BaseTypeServiceImpl<Modifier> implements IModifierService {

    @Autowired
    private IModifierDao modifierDao;

    @Override
    public IBaseMapper<Modifier> getBaseTypeDao() {
        return modifierDao;
    }
}
