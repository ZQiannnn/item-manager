package com.hand.services.impl;

import com.hand.models.Extension;
import com.hand.services.IFileService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title FileServiceImpl
 * @Description 对文件的操作
 * @Author ZQian
 * @date: 2017/8/2 下午6:23
 */
@Service
public class FileServiceImpl implements IFileService {


    private static Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

    private static final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*-items.xml");



    @Override
    public Map<String, Element> readExtension(Extension extension) throws IOException {

        Map<String, Element> result=new HashMap<>();
        //对extension path下的所有文件进行递归
        Files.walkFileTree(Paths.get(extension.getPath()), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(file)){
                    //当文件名符合*-items.xml时，读取文件
                    readFile(file, result);
                }
                //继续递归
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                //读取文件失败时，继续递归
                return FileVisitResult.CONTINUE;
            }
        });
        return result;
    }

    private void readFile(Path file, Map<String, Element> map) throws IOException {
        SAXReader reader = new SAXReader();
        try {
            //用dom4j读取xml文件
            Document document = reader.read(Files.newBufferedReader(file, Charset.forName("UTF-8")));
            Element collectiontypes = document.getRootElement().element("collectiontypes");
            Element enumtypes = document.getRootElement().element("enumtypes");
            Element itemtypes = document.getRootElement().element("itemtypes");
            Element relations = document.getRootElement().element("relations");
            Element atomictypes = document.getRootElement().element("atomictypes");
            Element maptypes = document.getRootElement().element("maptypes");

            //将读取出来的4种元素封装好作为结果返回
            map.put("collectiontypes",collectiontypes);
            map.put("enumtypes",enumtypes);
            map.put("itemtypes",itemtypes);
            map.put("relations",relations);
            map.put("atomictypes",atomictypes);
            map.put("maptypes",maptypes);
        } catch (DocumentException e) {
            LOG.error("path====="+file,e);
        }
    }

}
