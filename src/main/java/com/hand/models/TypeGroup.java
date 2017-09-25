package com.hand.models;

import javax.persistence.*;
import java.util.List;

/**
 * @Title TypeGroup
 * @Description Hybris的typegroup
 * @Author ZQian
 * @date: 2017/8/2 下午4:38
 */
@Table(name = "typegroup")
public class TypeGroup extends Item {

    @Id
    private String code;

    @Transient
    private List<ItemType> itemtypes;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ItemType> getItemtypes() {
        return itemtypes;
    }

    public void setItemtypes(List<ItemType> itemtypes) {
        this.itemtypes = itemtypes;
    }

}
