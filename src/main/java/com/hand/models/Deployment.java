package com.hand.models;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title Deployment
 * @Description Type映射的表
 * @Author ZQian
 * @date: 2017/8/2 下午4:12
 */
@Table(name = "deployment")
public class Deployment extends Item{
    //表名

    private String name;
    //code  TODO 要不要采用自动生成器
    @Id
    private String code;

    //对应的type或relation的code
    private String typeCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
