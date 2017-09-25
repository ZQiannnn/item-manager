package com.hand.models;

import java.io.Serializable;

/**
 * @Title GlobalSystem
 * @Description 系统配置表
 * @Author ZQian
 * @date: 2017/8/2 下午4:25
 */
public class Configuration implements Serializable{

    //系统是否已经初始化
    private Boolean initialized;

    //Hybris系统的路径
    private String location;


    public Boolean getInitialized() {
        return initialized;
    }

    public void setInitialized(Boolean initialized) {
        this.initialized = initialized;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
