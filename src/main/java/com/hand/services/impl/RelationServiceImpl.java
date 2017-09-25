package com.hand.services.impl;

import com.hand.daos.IRelationDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.Deployment;
import com.hand.models.Modifier;
import com.hand.models.Relation;
import com.hand.models.RelationValue;
import com.hand.services.IDeploymentService;
import com.hand.services.IModifierService;
import com.hand.services.IRelationService;
import com.hand.services.IRelationValueService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title RelationServiceImpl
 * @Description Hybris Relation处理的实现类
 * @Author ZQian
 * @date: 2017/8/7 下午3:36
 */
@Service
public class RelationServiceImpl extends BaseTypeServiceImpl<Relation> implements IRelationService {

    private static final Logger LOG = LoggerFactory.getLogger(RelationServiceImpl.class);

    @Autowired
    private IRelationDao relationDao;

    @Autowired
    private IDeploymentService tableService;

    @Autowired
    private IRelationValueService relationValueService;

    @Autowired
    private IModifierService modifierService;


    @Override
    public void insertList(List<Relation> relations) {
        if (CollectionUtils.isEmpty(relations))return;
        //建变量读出所有值采用一条insert插入
        List<RelationValue> relationValues=new ArrayList<>();
        List<Deployment> tables=new ArrayList<>();
        List<Modifier> modifiers=new ArrayList<>();
        //存relation
        super.insertList(relations);

        //迭代取值
        for (Relation relation : relations) {
            if (relation.getTable()!=null)
            tables.add(relation.getTable());

            relationValues.add(relation.getSource());
            modifiers.add(relation.getSource().getModifier());
            relationValues.add(relation.getTarget());
            modifiers.add(relation.getTarget().getModifier());
        }

        //批量存
        relationValueService.insertList(relationValues);
        tableService.insertList(tables);
        modifierService.insertList(modifiers);


    }

    @Override
    public IBaseMapper<Relation> getBaseTypeDao() {
        return relationDao;
    }
}
