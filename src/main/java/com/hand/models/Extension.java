package com.hand.models;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Title Extension
 * @Description Hybris的extension
 * @Author ZQian
 * @date: 2017/8/2 下午7:46
 */
public class Extension implements Comparable,Serializable {

    //名称
    @Id
    private String name;

    //路径
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    /**
     * 重写比较的方法，若name一致则extension一致
     *
     * @return
     */
    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Extension) {
            return ((Extension) obj).name.equals(this.getName());
        }
        return super.equals(obj);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Extension) {
            return this.getName().compareTo(((Extension) o).name);
        }
        return 0;
    }
}
