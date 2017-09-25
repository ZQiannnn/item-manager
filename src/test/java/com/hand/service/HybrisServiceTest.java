package com.hand.service;

import com.hand.BaseJunit4Test;
import com.hand.services.IExtensionService;
import com.hand.services.IHybrisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Title HybrisServiceTest
 * @Description hybrisservice单元测试类
 * @Author ZQian
 * @date: 2017/8/2 下午7:49
 */
public class HybrisServiceTest extends BaseJunit4Test {



    @Autowired
    private IHybrisService hybrisService;

    @Autowired
    private IExtensionService extensionService;


    @Test
    public void testScanHybrisExtensions() throws IOException {
        LOG.info("扫描HybrisExtension");
        hybrisService.scanHybrisExtensions("/Users/zhang/Work/WorkSpace/yth/hybris");
        if (extensionService.selectCount(null)==0){
            extensionService.insertList(new ArrayList<>(hybrisService.getExtensions()));
        }

    }



}
