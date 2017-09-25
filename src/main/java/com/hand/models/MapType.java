package com.hand.models;

import javax.persistence.Table;

/**
 * @Title MapType
 * @Description Hybris maptype
 * @Author ZQian
 * @date: 2017/8/9 上午11:13
 */
@Table(name="types")
public class MapType extends AbstractType {

    private String keyType;
    private String valueType;

    public MapType() {
        this.setTypeKey("maptype");
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
