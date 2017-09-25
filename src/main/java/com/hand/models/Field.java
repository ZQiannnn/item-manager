package com.hand.models;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Title Field
 * @Description Hybris 字段实体
 * @Author ZQian
 * @date: 2017/8/2 下午4:07
 */
@Table(name = "attribute")
public class Field extends Item {

    //qualifier
    @Id
    private String qualifier;

    //type
    @Id
    private String typeCode;

    private String attrType;

    @Transient
    private AbstractType type;


    private Boolean localized;

    //默认值
    private String defaultValue;

    //权限和唯一
    @Transient
    private Modifier modifier;

    //数据库列
    @Transient
    private Persistence persistence;

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public AbstractType getType() {
        return type;
    }

    public void setType(AbstractType type) {
        this.type = type;
    }

    public Modifier getModifier() {
        return modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }

    public Persistence getPersistence() {
        return persistence;
    }

    public void setPersistence(Persistence persistence) {
        this.persistence = persistence;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Boolean getLocalized() {
        return localized;
    }

    public void setLocalized(Boolean localized) {
        this.localized = localized;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
