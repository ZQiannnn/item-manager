package com.hand.models;

import com.hand.enums.Cardinality;
import com.hand.enums.CollectionEnum;
import com.hand.enums.RelationRole;

import javax.persistence.*;

/**
 * @Title RelationValue
 * @Description 关系中的一边
 * @Author ZQian
 * @date: 2017/8/2 下午5:05
 */
@Table(name = "relationValue")
public class RelationValue extends Item {
    //qualifier
    @Id
    private String qualifier;

    //关系索指向的type
    private String attrType;
    //type
    @Transient
    private AbstractType type;

    //关系的结构  N或1
    private Cardinality cardinality;
    //权限
    @Transient
    private Modifier modifier;

    //多的时候的CollectionType
    private CollectionEnum collectionType;

    //是否可查找
    private Boolean navigable;

    //父关系的code
    @Id
    private String relationCode;

    //多或者1
    private RelationRole relationRole;

    //是否排序
    private Boolean ordered;

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public AbstractType getType() {
        return type;
    }

    public void setType(AbstractType type) {
        this.type = type;
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public void setCardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
    }

    public Modifier getModifier() {
        return modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }

    public CollectionEnum getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(CollectionEnum collectionType) {
        this.collectionType = collectionType;
    }

    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    public RelationRole getRelationRole() {
        return relationRole;
    }

    public void setRelationRole(RelationRole relationRole) {
        this.relationRole = relationRole;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }


    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public Boolean getNavigable() {
        return navigable;
    }

    public void setNavigable(Boolean navigable) {
        this.navigable = navigable;
    }
}
