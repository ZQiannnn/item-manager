package com.hand.models;

import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Title Relation
 * @Description Hybris中的关系实体  relation继承abstracttype结构
 * @Author ZQian
 * @date: 2017/8/2 下午5:02
 */
@Table(name = "types")
public class Relation extends AbstractType {


    //国际化
    private Boolean localized;


    //多对多中的table
    @Transient
    private Deployment table;

    //目标
    @Transient
    private RelationValue source;

    //源
    @Transient
    private RelationValue target;

    public Relation() {
        this.setTypeKey("relation");
    }

    public Deployment getTable() {
        return table;
    }

    public void setTable(Deployment table) {
        this.table = table;
    }

    public Boolean getLocalized() {
        return localized;
    }

    public void setLocalized(Boolean localized) {
        this.localized = localized;
    }


    public RelationValue getSource() {
        return source;
    }

    public void setSource(RelationValue source) {
        this.source = source;
    }

    public RelationValue getTarget() {
        return target;
    }

    public void setTarget(RelationValue target) {
        this.target = target;
    }
}
