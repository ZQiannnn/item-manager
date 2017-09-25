package com.hand.models;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title CustomProperties
 * @Description ItemType的custom-properties
 * @Author ZQian
 * @date: 2017/8/8 上午11:22
 */
@Table(name = "property")
public class CustomProperty extends Item{

    //对应的type的code
    @Id
    private String typeCode;
    @Id
    private String name;

    private String value;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
