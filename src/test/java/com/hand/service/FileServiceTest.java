package com.hand.service;

import com.hand.BaseJunit4Test;
import com.hand.models.Extension;
import com.hand.services.IExtensionService;
import com.hand.services.IFileService;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

/**
 * @Title UnitServiceTest
 * @Description 单位-单元测试类
 * @Author ZQian
 * @date: 2017/8/2 上午11:11
 */
public class FileServiceTest extends BaseJunit4Test {



    @Autowired
    private IFileService fileService;

    @Autowired
    private IExtensionService extensionService;

    @Test
    public void testReadDirectory() throws IOException {
        Extension extension = extensionService.selectByPrimaryKey("hepcore");
        Map<String, Element> elementMap = fileService.readExtension(extension);
        Assert.assertTrue(elementMap.size()>0);
    }


}
