package com.hand.models;

import java.io.Serializable;

/**
 * @Title Item
 * @Description 所有model类型的父类
 * @Author ZQian
 * @date: 2017/8/5 下午2:02
 */
public class Item implements Serializable {

    //是否在custom下
    private Boolean custom;

    //描述
    private String description;



    public Boolean getCustom() {
        return custom;
    }

    public void setCustom(Boolean custom) {
        this.custom = custom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
