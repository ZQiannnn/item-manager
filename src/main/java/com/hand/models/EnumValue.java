package com.hand.models;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title EnumValue
 * @Description 枚举值
 * @Author ZQian
 * @date: 2017/8/2 下午4:59
 */
@Table(name = "enumvalue")
public class EnumValue extends Item {

    @Id
    private String code;

    @Id
    @Column(name = "enumCode")
    private String enumCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getEnumCode() {
        return enumCode;
    }

    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }
}
