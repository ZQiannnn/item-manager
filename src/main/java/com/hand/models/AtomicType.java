package com.hand.models;

import javax.persistence.Table;

/**
 * @Title AtomicType
 * @Description Hybris AtomicType
 * @Author ZQian
 * @date: 2017/8/9 上午10:48
 */
@Table(name = "types")
public class AtomicType extends AbstractType{

    private String extend;

    public AtomicType() {
        this.setTypeKey("atomictype");
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }
}
