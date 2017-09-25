package com.hand.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @Title Modifier
 * @Description attr的权限控制
 * @Author ZQian
 * @date: 2017/8/2 下午5:05
 */
public class Modifier extends Item{

    //Modifier关联的type和qualifier作为组合主键
    @Id
    private String typeCode;
    @Id
    private String qualifier;

    @Column(name = "m_unique")
    private Boolean unique;
    @Column(name = "m_read")
    private Boolean read;
    @Column(name = "m_write")
    private Boolean write;
    @Column(name = "m_optional")
    private Boolean optional;
    @Column(name = "m_search")
    private Boolean search;

    public Boolean getUnique() {
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public Boolean getSearch() {
        return search;
    }

    public void setSearch(Boolean search) {
        this.search = search;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
}
