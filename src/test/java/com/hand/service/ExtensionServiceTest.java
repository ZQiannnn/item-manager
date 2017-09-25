package com.hand.service;

import com.hand.BaseTransactionalJunit4Test;
import com.hand.services.IExtensionService;
import com.hand.services.IHybrisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Title ExtensionServiceTest
 * @Description ExtensionService的测试类
 * @Author ZQian
 * @date: 2017/8/3 下午5:24
 */
public class ExtensionServiceTest extends BaseTransactionalJunit4Test {

    @Autowired
    private IHybrisService hybrisService;

    @Autowired
    private IExtensionService extensionService;



    @Test
    public void testInsert() throws IOException {
        hybrisService.scanHybrisExtensions("/Users/zhang/Work/WorkSpace/yth/hybris");
        LOG.info("size======"+hybrisService.getExtensions().size());
        extensionService.insertList(new ArrayList<>(hybrisService.getExtensions()));
        LOG.info("count======"+extensionService.selectCount(null));
    }


}
