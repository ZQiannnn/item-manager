package com.hand.models;

import javax.persistence.Table;

/**
 * @Title Test
 * @Description
 * @Author ZQian
 * @date: 2017/9/13 下午3:56
 */
@Table(name="test")
public class Test {
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
