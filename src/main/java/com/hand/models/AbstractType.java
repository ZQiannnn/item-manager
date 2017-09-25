package com.hand.models;

import javax.persistence.Id;

/**
 * @Title AbstractType
 * @Description 抽象Type-枚举、Collection、type的父类
 * @Author ZQian
 * @date: 2017/8/2 下午4:48
 */
public class AbstractType extends Item {

    @Id
    private String code;


    //所属extension
    private String extension;

    //用来保存的是什么类型
    private String typeKey;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }
}
