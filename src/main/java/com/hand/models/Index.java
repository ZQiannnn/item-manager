package com.hand.models;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @Title Index
 * @Description Hybris 索引
 * @Author ZQian
 * @date: 2017/8/8 下午3:49
 */
@Table(name = "indexes")
public class Index extends Item {

    @Id
    private String typeCode;

    @Id
    private String name;

    @Column(name = "i_unique")
    private Boolean unique;

    @Column(name = "i_keys")
    private List<String> keys;


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

    public Boolean getUnique() {
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }
}
