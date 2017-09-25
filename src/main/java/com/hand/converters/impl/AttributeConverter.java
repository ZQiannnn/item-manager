package com.hand.converters.impl;

import com.hand.beans.UserException;
import com.hand.converters.Converter;
import com.hand.enums.PersistenceType;
import com.hand.models.Extension;
import com.hand.models.Field;
import com.hand.models.Modifier;
import com.hand.models.Persistence;
import com.hand.services.IBaseTypeService;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title AttributeConverter
 * @Description Hybris Attribute的转换器
 * @Author ZQian
 * @date: 2017/8/8 上午11:48
 */
@Component
public class AttributeConverter implements Converter<Field> {

    @Autowired
    private IBaseTypeService<Modifier> modifierService;

    @Autowired
    private IBaseTypeService<Persistence> persistenceService;

    @Override
    public Field convert(Element element, Extension extension) {
        String qualifier = "";
        String typecode = "";
        try {
            Field field = new Field();
            //qualifier  一定有
            qualifier = element.attribute("qualifier").getValue();
            field.setQualifier(qualifier);

            //typecode  一定有
            typecode = element.getParent().getParent().attribute("code").getValue();
            field.setTypeCode(typecode);


            //redeclare 不支持

            //type 一定有
            String type=element.attribute("type").getValue();
            field.setAttrType(type);

            //localized
            field.setLocalized(type.contains("localized:"));

            //metatype不支持  isSelectionOf 不支持

            //description
            String description = element.elementText("description");
            field.setDescription(description);


            //modifiers
            Element modifiersElement = element.element("modifiers");
            if (modifiersElement!=null){
                List<Modifier> modifiers = modifierService.resolveElementType(element, extension, "modifiers", insertTrigger);
                field.setModifier(modifiers.iterator().next());
            }else {
                Modifier modifier=new Modifier();
                modifier.setUnique(false);
                modifier.setOptional(true);
                modifier.setSearch(true);
                modifier.setWrite(true);
                modifier.setRead(true);
                modifier.setCustom(true);
                modifier.setTypeCode(typecode);
                modifier.setQualifier(qualifier==null?"":qualifier);
                field.setModifier(modifier);
            }

            //persistence 列属性 不填生成默认值
            Element persistenceElement = element.element("persistence");
            if (persistenceElement!=null){
                List<Persistence> persistence = persistenceService.resolveElementType(element, extension, "persistence", insertTrigger);
                field.setPersistence(persistence.iterator().next());
            }else {
                Persistence persistence=new Persistence();
                persistence.setType(PersistenceType.property);
                persistence.setLongString(false);
                persistence.setQualifier(qualifier);
                persistence.setTypeCode(typecode);
                persistence.setCustom(true);
                field.setPersistence(persistence);
            }

            //defaultValue 可能有
            field.setDefaultValue(element.elementText("defaultvalue"));


            return field;

        } catch (Exception e) {
            LOG.error("解析Attribute出错", e);
            throw new UserException("Attribute:" + typecode + "-" + qualifier + "解析异常，请检查。");
        }
    }

    @Override
    public Element convert(Field field) {
        return null;
    }
}
