package com.hand.models;

import com.hand.enums.CollectionEnum;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Title CollectionType
 * @Description 集合类型
 * @Author ZQian
 * @date: 2017/8/2 下午4:54
 */
@Table(name = "types")
public class CollectionType extends AbstractType{

    @Column(name = "elementtype")
    private String elementtypeCode;

    //类型
    @Transient
    private AbstractType elementtype;

    //集合类型
    private CollectionEnum type;

    public CollectionType() {
        this.setTypeKey("collectiontype");
    }


    public AbstractType getElementtype() {
        return elementtype;
    }

    public void setElementtype(AbstractType elementtype) {
        this.elementtype = elementtype;
    }

    public CollectionEnum getType() {
        return type;
    }

    public void setType(CollectionEnum type) {
        this.type = type;
    }

    public String getElementtypeCode() {
        return elementtypeCode;
    }

    public void setElementtypeCode(String elementtypeCode) {
        this.elementtypeCode = elementtypeCode;
    }
}
