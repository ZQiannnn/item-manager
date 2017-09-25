package com.hand.models;

import com.hand.enums.PersistenceType;

import javax.persistence.Id;

/**
 * @Title Persistence
 * @Description 列的描述
 * @Author ZQian
 * @date: 2017/8/2 下午5:27
 */
public class Persistence extends Item{
    //类型
    private PersistenceType type;

    //为dynamic的时候提供的handler
    private String attributeHandler;

    //为property 时候是否为长字符串
    private Boolean longString;

    //Persistence关联的type和qualifier作为组合主键
    @Id
    private String typeCode;
    @Id
    private String qualifier;


    public PersistenceType getType() {
        return type;
    }

    public void setType(PersistenceType type) {
        this.type = type;
    }

    public String getAttributeHandler() {
        return attributeHandler;
    }

    public void setAttributeHandler(String attributeHandler) {
        this.attributeHandler = attributeHandler;
    }

    public Boolean getLongString() {
        return longString;
    }

    public void setLongString(Boolean longString) {
        this.longString = longString;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
}
