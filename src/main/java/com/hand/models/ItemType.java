package com.hand.models;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Title ItemType
 * @Description Hybris Type实体
 * @Author ZQian
 * @date: 2017/8/2 下午4:07
 */
@Table(name = "types")
public class ItemType extends AbstractType{

    private String typeGroup;

    //deployment
    @Transient
    private Deployment table;
    //jaloclass
    private String jaloClass;



    //继承自
    private String extend;

    //是否单例
    private Boolean singleton;

    //是否抽象
    private Boolean abstracts;

    //是否autocreate
    @Transient
    private Boolean autocreate;



    //attribute
    @Transient
    private List<Field> attributes;

    @Transient
    private List<Index> indexes;

    //父type
    @Transient
    private ItemType parent;
    //子type
    @Transient
    private List<ItemType> children;

    @Transient
    private List<CustomProperty> customProperties;


    public ItemType() {
        this.setTypeKey("itemtype");
    }

    public Deployment getTable() {
        return table;
    }

    public void setTable(Deployment table) {
        this.table = table;
    }

    public String getJaloClass() {
        return jaloClass;
    }

    public void setJaloClass(String jaloClass) {
        this.jaloClass = jaloClass;
    }

    public List<Field> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Field> attributes) {
        this.attributes = attributes;
    }

    public ItemType getParent() {
        return parent;
    }

    public void setParent(ItemType parent) {
        this.parent = parent;
    }

    public List<ItemType> getChildren() {
        return children;
    }

    public void setChildren(List<ItemType> children) {
        this.children = children;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getTypeGroup() {
        return typeGroup;
    }

    public void setTypeGroup(String typeGroup) {
        this.typeGroup = typeGroup;
    }

    public Boolean getSingleton() {
        return singleton;
    }

    public void setSingleton(Boolean singleton) {
        this.singleton = singleton;
    }

    public Boolean getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(Boolean abstracts) {
        this.abstracts = abstracts;
    }

    public List<CustomProperty> getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(List<CustomProperty> customProperties) {
        this.customProperties = customProperties;
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Index> indexes) {
        this.indexes = indexes;
    }

    public Boolean getAutocreate() {
        return autocreate;
    }

    public void setAutocreate(Boolean autocreate) {
        this.autocreate = autocreate;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemType){
            return this.getCode().equals(((ItemType) obj).getCode());
        }
       return false;
    }

    @Override
    public int hashCode() {
        return  this.getCode().hashCode();
    }
}
