package com.hand.services.impl;

import com.hand.daos.IItemTypeDao;
import com.hand.mapper.IBaseMapper;
import com.hand.models.*;
import com.hand.services.IBaseTypeService;
import com.hand.services.IItemTypeService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title ItemTypeServiceImpl
 * @Description itemtype处理实现类
 * @Author ZQian
 * @date: 2017/8/5 上午10:57
 */
@Service
public class ItemTypeServiceImpl extends BaseTypeServiceImpl<ItemType> implements IItemTypeService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemTypeServiceImpl.class);

    @Autowired
    private IItemTypeDao itemTypeDao;

    @Autowired
    private IBaseTypeService<Deployment> deploymentService;

    @Autowired
    private IBaseTypeService<CustomProperty> customPropertyService;

    @Autowired
    private IBaseTypeService<Field> attributeService;

    @Autowired
    private IBaseTypeService<Index> indexService;

    @Autowired
    private IBaseTypeService<Modifier> modifierService;

    @Autowired
    private IBaseTypeService<Persistence> persistenceService;

    @Override
    public IBaseMapper<ItemType> getBaseTypeDao() {
        return itemTypeDao;
    }


    @Override
    public void insertList(List<ItemType> itemTypes) {
        if (CollectionUtils.isEmpty(itemTypes)) return;
        //把所有级联元素拿出来一次插入
        List<ItemType> newItemType = new ArrayList<>();
        List<Deployment> deployments = new ArrayList<>();
        List<CustomProperty> properties = new ArrayList<>();
        List<Field> attributes = new ArrayList<>();
        List<Index> indexes = new ArrayList<>();
        List<Modifier> modifiers = new ArrayList<>();
        List<Persistence> persistences = new ArrayList<>();

        //先插入Type 再保存其他
        for (ItemType type : itemTypes) {
            //先查询
            if (itemTypeDao.selectByPrimaryKey(type.getCode()) != null) {
                //autocreate为true才更新 否则不操作
                if (type.getAutocreate())
                    itemTypeDao.updateByPrimaryKey(type);
            } else if (newItemType.contains(type)){
                //数据库中没有  但同一文件中code一样
                if (type.getAutocreate()){
                    //如果是autocreate以autocreate为准
                    newItemType.remove(type);
                    newItemType.add(type);
                }

            }else if (!newItemType.contains(type)){
                //数据库中没有且code不一样
                newItemType.add(type);
            }
            List<Field> fields = type.getAttributes();
            if (CollectionUtils.isNotEmpty(fields)) {
                attributes.addAll(fields);
                for (Field field : fields) {
                    modifiers.add(field.getModifier());
                    persistences.add(field.getPersistence());
                }
            }
            if (type.getTable() != null) {
                deployments.add(type.getTable());
            }
            if (CollectionUtils.isNotEmpty(type.getCustomProperties())) {
                properties.addAll(type.getCustomProperties());
            }
            if (type.getIndexes() != null) {
                indexes.addAll(type.getIndexes());
            }

        }
//        LOG.info("保存ItemType的数量：" + count);
//        LOG.info("保存Deployment的数量：" + deployments.size());
//        LOG.info("保存CustomProperty的数量：" + properties.size());
//        LOG.info("保存Attribute的数量：" + attributes.size());
//        LOG.info("保存Index的数量：" + indexes.size());
//        LOG.info("保存Modifier的数量：" + modifiers.size());
//        LOG.info("保存Persistence的数量：" + persistences.size());
        if (CollectionUtils.isNotEmpty(newItemType)) itemTypeDao.insertList(newItemType);
        deploymentService.insertList(deployments);
        customPropertyService.insertList(properties);
        attributeService.insertList(attributes);
        indexService.insertList(indexes);
        modifierService.insertList(modifiers);
        persistenceService.insertList(persistences);

    }
}
