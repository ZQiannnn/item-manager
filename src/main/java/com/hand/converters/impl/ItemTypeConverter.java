package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.models.*;
import com.hand.services.IBaseTypeService;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title ItemtypeConverter
 * @Description Itemtype的转换器   itemtype的element是有重复code，且可以在基础上新增的
 * @Author ZQian
 * @date: 2017/8/5 下午1:34
 */
@Component
public class ItemTypeConverter implements Converter<ItemType> {


    @Autowired
    private IBaseTypeService<Deployment> deploymentService;

    @Autowired
    private IBaseTypeService<CustomProperty> customPropertyService;

    @Autowired
    private IBaseTypeService<Field> attributeService;

    @Autowired
    private IBaseTypeService<Index> indexService;

    @Override
    public ItemType convert(Element element, Extension extension) {
        String code = "";
        try {
            ItemType itemType=new ItemType();
            //code  一定有
            code = element.attribute("code").getValue();
            itemType.setCode(code);

            //description 可以为空
            Element descElement = element.element("description");
            if (!elementIsEmpty(descElement)){
                itemType.setDescription(descElement.getTextTrim());
            }

            //deployment 可以为空
            Element deployElement = element.element("deployment");
            if (deployElement!=null){
                List<Deployment> deployment = deploymentService.resolveElementType(element, extension, "deployment", insertTrigger);
                itemType.setTable(deployment.iterator().next());
            }

            //typeGroup 可以为空
            Element parent = element.getParent();
            if (parent.getName().equals("typegroup")){
                itemType.setTypeGroup(parent.attribute("name").getValue());
            }

            //extends 可以为空  为空默认GenericItem
            Attribute extendsAttr = element.attribute("extends");
            if (attrIsEmpty(extendsAttr)){
                itemType.setExtend("GenericItem");
            }else {
                itemType.setExtend(extendsAttr.getValue());
            }

            itemType.setAutocreate(isAutocreate(element));

            //jaloclass 可以为空  为空自动指定，不设默认值
            itemType.setJaloClass(element.attributeValue("jaloclass"));

            //deploymentAttr 不支持 Deprecated, please use separate deployment sub tag.

            //singleton 可以为空 默认false
            Attribute singletonAttr = element.attribute("singleton");
            if (attrIsEmpty(singletonAttr)){
                itemType.setSingleton(Boolean.FALSE);
            }else {
                itemType.setSingleton(Boolean.valueOf(singletonAttr.getValue()));
            }

            //jaloonly不支持 DEPRECATED. Use 'implements JaloOnlyItem' in your bean. If 'true',
            // the item will only exists in the jalo layer and isn't backed by an entity bean. Default is 'false'.


            //abstract
            Attribute abstractAttr = element.attribute("abstract");
            if (attrIsEmpty(abstractAttr)){
                itemType.setAbstracts(Boolean.FALSE);
            }else {
                itemType.setAbstracts(Boolean.valueOf(abstractAttr.getValue()));
            }

            //metatype 不支持


            //custom-properties  非必输
            Element propertiesElement = element.element("custom-properties");
            if (propertiesElement!=null){
                List<CustomProperty> properties = customPropertyService.resolveElementType(propertiesElement, extension, "property", insertTrigger);
                itemType.setCustomProperties(properties);
            }

            //attributes  非必输
            Element attributesElement = element.element("attributes");
            if (attributesElement!=null){
                List<Field> properties = attributeService.resolveElementType(attributesElement, extension, "attribute", insertTrigger);
                itemType.setAttributes(properties);
            }

            //indexes 非必输
            Element indexesElement = element.element("indexes");
            if (indexesElement!=null){
                List<Index> indexes = indexService.resolveElementType(indexesElement, extension, "index", insertTrigger);
                itemType.setIndexes(indexes);
            }


            return itemType;

        } catch (Exception e) {
            LOG.error("解析ItemType出错",e);
            throw new UserException("ItemType:"+code+"解析异常，请检查。");
        }
    }

    @Override
    public Element convert(ItemType itemtype) {
        return null;
    }
}
