package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.enums.Cardinality;
import com.hand.enums.CollectionEnum;
import com.hand.enums.RelationRole;
import com.hand.models.Extension;
import com.hand.models.Modifier;
import com.hand.models.RelationValue;
import com.hand.services.IBaseTypeService;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title RelationValueConverter
 * @Description RelationValue的转换器
 * @Author ZQian
 * @date: 2017/8/7 下午4:15
 */
@Component
public class RelationValueConverter implements Converter<RelationValue> {

    @Autowired
    private IBaseTypeService<Modifier> modifierService;


    @Override
    public RelationValue convert(Element element, Extension extension) {
        //父Relation的code
        String parentCode = "";
        //自己的标识符
        String qualifier = "";
        try {
            RelationValue value=new RelationValue();
            //parentCode  一定有
            parentCode = element.getParent().attribute("code").getValue();
            value.setRelationCode(parentCode);

            //navigable   是否可查找 默认true
            Attribute navigableAttr = element.attribute("navigable");
            if (attrIsEmpty(navigableAttr)){
                value.setNavigable(true);
            }else{
                value.setNavigable(Boolean.valueOf(navigableAttr.getValue()));
            }

            //qualifier  navigable为false的时候可以没有
            qualifier = element.attributeValue("qualifier");
            value.setQualifier(qualifier==null?"":qualifier);

            //根据自己的name 判断是source还是target
            String name = element.getName();
            if (name.contains("source")){
                value.setRelationRole(RelationRole.source);
            }else {
                value.setRelationRole(RelationRole.target);
            }

            //type 一定有
            String attrType = element.attribute("type").getValue();
            value.setAttrType(attrType);

            //type为optional的，不输默认collection
            Attribute collectiontypeAttr = element.attribute("collectiontype");
            if (attrIsEmpty(collectiontypeAttr)){
                value.setCollectionType(CollectionEnum.collection);
            }else{
                value.setCollectionType(CollectionEnum.valueOf(collectiontypeAttr.getValue()));
            }

            //cardinality为optional，不输默认many
            Attribute cardinalityAttr = element.attribute("cardinality");
            if (attrIsEmpty(cardinalityAttr)){
                value.setCardinality(Cardinality.many);
            }else{
                value.setCardinality(Cardinality.valueOf(cardinalityAttr.getValue()));
            }

            //ordered为optional，不输默认为false
            Attribute orderedAttr = element.attribute("ordered");
            if (attrIsEmpty(orderedAttr)){
                value.setOrdered(false);
            }else{
                value.setOrdered(Boolean.valueOf(orderedAttr.getValue()));
            }

            //Modifier处理
            if (element.element("modifiers")!=null){
                List<Modifier> modifiers = modifierService.resolveElementType(element, extension, "modifiers", insertTrigger);
                value.setModifier(modifiers.iterator().next());
            }else{
                Modifier modifier=new Modifier();
                modifier.setUnique(false);
                modifier.setOptional(true);
                modifier.setSearch(true);
                modifier.setWrite(true);
                modifier.setRead(true);
                modifier.setCustom(true);
                modifier.setTypeCode(parentCode);
                modifier.setQualifier(qualifier==null?"":qualifier);
                value.setModifier(modifier);
            }
            return value;

        } catch (Exception e) {
            LOG.error("解析RelationValue出错",e);
            throw new UserException("RelationValue:"+parentCode+"-"+qualifier+"解析异常，请检查。");
        }

    }

    @Override
    public Element convert(RelationValue relationValue) {
        return null;
    }
}
