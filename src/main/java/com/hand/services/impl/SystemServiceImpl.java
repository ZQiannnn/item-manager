package com.hand.services.impl;

import com.hand.models.Configuration;
import com.hand.models.Extension;
import com.hand.services.*;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Title SystemServiceImpl
 * @Description 系统处理  实现类
 * @Author ZQian
 * @date: 2017/8/8 下午7:35
 */
@Service
public class SystemServiceImpl implements ISystemService {

    private static Logger LOG = LoggerFactory.getLogger(SystemServiceImpl.class);

    //用来保存当前系统初始化到哪步了
    private static Queue<String> status = new ArrayBlockingQueue<>(1);

    private String msg;


    @Autowired
    private IHybrisService hybrisService;

    @Autowired
    private IExtensionService extensionService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private ICollectionTypeService collectionTypeService;

    @Autowired
    private IEnumTypeService enumTypeService;

    @Autowired
    private ITypeGroupService typeGroupService;

    @Autowired
    private IItemTypeService itemTypeService;
    @Autowired
    private IRelationService relationService;

    @Autowired
    private IAtomicTypeService atomicTypeService;

    @Autowired
    private IMapTypeService mapTypeService;


    @Autowired
    private IConfigurationService configurationService;


    @Override
    public void init() throws IOException {
        //获取系统配置
        Configuration configuration = configurationService.selectOne(null);

        //获得hybris的location
        String location = configuration.getLocation();

        //解析Extension
        submitStatus("正在读取Extensions");
        hybrisService.scanHybrisExtensions(location);

        extensionService.insertList(new ArrayList<>(hybrisService.getExtensions()));

        LOG.info("解析Extension共："+hybrisService.getExtensions().size()+"个");

        //分别读取Extension
        for (Extension extension : hybrisService.getExtensions()) {

            LOG.info("正在解析Extension  :  " + extension.getName()+"...");

            submitStatus("正在解析Extension  :  " + extension.getName()+"...");
            //获得Extension的元素节点
            Map<String, Element> result = fileService.readExtension(extension);

            //分别处理各个元素


            //atomictype
            submitStatus("正在解析Extension  :  " + extension.getName() + "   -AtomicTypes...");
            atomicTypeService.resolveElementType(result.get("atomictypes"),
                    extension, "atomictype", true);


            //maptype
            submitStatus("正在解析Extension  :  " + extension.getName() + "   -MapTypes...");
            mapTypeService.resolveElementType(result.get("maptypes"),
                    extension, "maptype", true);

            //collectiontype
            submitStatus("正在解析Extension  :  " + extension.getName() + "   -CollectionTypes...");
            collectionTypeService.resolveElementType(result.get("collectiontypes"),
                    extension, "collectiontype", true);

            //enumtype
            submitStatus("正在解析Extension  :  " + extension.getName() + "   -EnumTypes...");
            enumTypeService.resolveElementType(result.get("enumtypes"),
                    extension, "enumtype", true);

            //relation
            submitStatus("正在解析Extension  :  " + extension.getName() + "   -Relations...");
            relationService.resolveElementType(result.get("relations"),
                    extension, "relation", true);

            //itemtype
            submitStatus("正在解析Extension  :  " + extension.getName() + "   -ItemTypes...");
            typeGroupService.resolveElementType(result.get("itemtypes"),
                    extension, "typegroup", true);
            itemTypeService.resolveElementType(result.get("itemtypes"),
                    extension, "typegroup/itemtype", true);


        }

    }

    private void submitStatus(String msg) {
        status.poll();
        status.offer(msg);
    }

    @Override
    public String getStatus() {
        String poll = status.poll();
        msg = poll != null ? poll : msg;
        return msg;
    }
}
