package com.hand.models;

import javax.persistence.*;
import java.util.List;

/**
 * @Title EnumType
 * @Description Hybris枚举
 * @Author ZQian
 * @date: 2017/8/2 下午4:27
 */
@Table(name = "types")
public class EnumType extends AbstractType {

    //枚举值列表  不存至数据库


    @Transient
    private List<EnumValue> values;

    @Transient
    private Boolean autocreate;

    private Boolean dynamic;

    public EnumType() {
        this.setTypeKey("emumtype");
    }

    public List<EnumValue> getValues() {
        return values;
    }

    public void setValues(List<EnumValue> values) {
        this.values = values;
    }

    public Boolean getDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public Boolean getAutocreate() {
        return autocreate;
    }

    public void setAutocreate(Boolean autocreate) {
        this.autocreate = autocreate;
    }
}
