package com.hand.service;

import com.hand.BaseTransactionalJunit4Test;
import com.hand.daos.ITestDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Title LongTest
 * @Description
 * @Author ZQian
 * @date: 2017/9/13 下午3:58
 */
public class LongTest extends BaseTransactionalJunit4Test{

    @Autowired
    private ITestDao testDao;

    @Test
    public void test(){
        String s = "";
        for (int i = 0; i < 4001; i++) {
            s=s.concat("嬴");
        }
        System.out.println(s.length());
        com.hand.models.Test t = new com.hand.models.Test();
        t.setTest(s);
        testDao.insert(t);
    }
}
