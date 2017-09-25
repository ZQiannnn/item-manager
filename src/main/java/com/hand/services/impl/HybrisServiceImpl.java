package com.hand.services.impl;

import com.hand.models.Extension;
import com.hand.services.IHybrisService;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title HybrisService
 * @Description Hybris相关的service实现类
 * @Author ZQian
 * @date: 2017/8/2 下午7:49
 */
@Service
public class HybrisServiceImpl implements IHybrisService {

    private static final Logger LOG = LoggerFactory.getLogger(HybrisServiceImpl.class);

    private static String hybrisBinDir;

    private static String hybrisConfigDir;

    // //新建Set存储Extension 存到内存中
    private static Set<Extension> extensions = new TreeSet<>();

    @Override
    public Set<Extension> getExtensions() {
        return extensions;
    }

    @Override
    public void scanHybrisExtensions(String path) throws IOException {
        //找到Hybris的localextension.xml文件并解析

        hybrisBinDir = path + "/bin";
        hybrisConfigDir = path + "/config";


        //新建一个扫描器
        Scanner scanner = new Scanner();

        //向扫描器注册路径  先把platform底下所有的Extension加入
        scanner.register(hybrisBinDir + "/platform", null);

        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(Files.newBufferedReader(Paths.get(hybrisConfigDir + "/localextensions.xml")));

            //先选取所有的path
            List<Element> paths = (List<Element>) document.selectNodes("//path");

            for (Element element : paths) {
                //找autoload为true的path
                Attribute autoload = element.attribute("autoload");
                if (autoload != null && Boolean.parseBoolean(autoload.getValue())) {
                    //读取该目录底下的extension和扫描依赖
                    Attribute dirAttr = element.attribute("dir");
                    String dir = dirAttr.getValue().replaceAll(".*HYBRIS_BIN_DIR}", hybrisBinDir);
                    //在这个文件夹下找Extension
                    scanner.register(dir, null);
                }
            }

            //选取索所有的extension
            List<Element> extensionElements = (List<Element>) document.selectNodes("//extension");
            for (Element element : extensionElements) {
                if (element.attribute("dir") != null && StringUtils.isNotEmpty(element.attribute("dir").getValue())) {
                    //以dir为准
                    scanner.register(element.attribute("dir").getValue().replaceAll(".*HYBRIS_BIN_DIR}", hybrisBinDir), null);
                } else if (element.attribute("name") != null && StringUtils.isNotEmpty(element.attribute("name").getValue())) {
                    //以name为准
                    scanner.register(hybrisBinDir, element.attribute("name").getValue());
                }

            }
            //执行扫描
            scanner.execte();

        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }

    /**
     * Extension扫描器
     */
    class Scanner {


        private static final String suffix = "extensioninfo.xml";
        private Map<String, List<String>> scanners;
        private Map<Integer, Set<String>> dependencyScanners;

        //是否开启了hmc
        private Boolean hmcEnable;

        Scanner() {
            this.scanners = new HashMap<>();
            this.dependencyScanners = new ConcurrentHashMap<>();
        }

        /**
         * 注册一个扫描路径和扫描的extension
         *
         * @param path      路径
         * @param extension 限定extension
         */
        void register(String path, String extension) {
            if (StringUtils.isNotEmpty(extension)) {
                if (scanners.containsKey(path)) {
                    scanners.get(path).add(extension);
                } else {
                    List<String> list = Arrays.asList(extension);
                    scanners.put(path, new ArrayList<>(list));
                }
            } else {
                scanners.put(path, new ArrayList<>());
            }
        }

        /**
         * 注册Extension的依赖
         * @param extension
         * @param i
         */
        void registerDependency(String extension,int i) {
            //定义两级结构，map的key代表层级，每一个Set代表第x层的依赖，对每一个Set进行files.walkFileTree
            if (dependencyScanners.containsKey(i)) {
                dependencyScanners.get(i).add(extension);
            } else {
                List<String> list = Arrays.asList(extension);
                dependencyScanners.put(i, new HashSet<>(list));
            }
        }

        /**
         * 对注册的扫描路径进行扫描
         */
        void execte() throws IOException {
            //计算hmc是否在第一次注册的Extension中
            this.scanners.forEach((key,value)->{
                if(value.contains("hmc")){
                    hmcEnable=true;
                }
            });

            this.scanners.forEach((path, extension) -> {
                //如果Extensions不为空  正则表达式为 .*/(extension1,extension2)/extension.xml 为空则为 .*()/extension.xml

                //因为不为空是全目录查找   不加 / hmc会匹配上xxxhmc
                try {
                    StringBuilder regex = new StringBuilder(extension.isEmpty() ? ".*(" : ".*/(");
                    for (String s : extension) {
                        regex.append("|").append(s);
                    }
                    regex.replace(extension.isEmpty() ? 3 : 4, extension.isEmpty() ? 4 : 5, "").append(")/").append(suffix);


                    scanDirectory(path, regex, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //扫描完第一级并且注入了依赖
            });
            LOG.info("第1层Extension处理完成，共Extension：" + getExtensions().size() + "个");
            //第一级依赖注册完成 把依赖的Extension扫描出来
            Iterator<Map.Entry<Integer, Set<String>>> iterator = dependencyScanners.entrySet().iterator();
            int i=1;
            while (iterator.hasNext()) {
                Map.Entry<Integer, Set<String>> mapEntry = iterator.next();
                LOG.info("第"+i+"层的依赖共："+ mapEntry.getValue().size()+"个");
                StringBuilder regex = new StringBuilder(".*/(");
                for (String s : mapEntry.getValue()) {
                    regex.append("|").append(s);
                }
                regex.replace(4, 5, "").append(")/").append(suffix);
                i++;
                scanDirectory(hybrisBinDir, regex,i);
                iterator.remove();
                iterator=dependencyScanners.entrySet().iterator();
            }
        }

        /**
         * 扫描文件夹下的Extension
         *
         * @param path  路径
         * @param regex extension正则
         * @param i
         * @throws IOException
         */
        private void scanDirectory(String path, StringBuilder regex, int i) throws IOException {
            Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    //扫描限定的extensioninfo.xml生成extension并且注册他们的依赖
                    if (file.toString().matches(regex.toString())) {
                        Extension e = resolveXmlPathToExtension(file.toString());
                        resolveExtension(e,i);
                    }

                    return super.visitFile(file, attrs);
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        }


        /**
         * 解析extensioninfo.xml路径 to Extension
         *
         * @param path
         */
        private Extension resolveXmlPathToExtension(String path) {
            File dir = new File(path).getParentFile();
            String name = dir.getName();
            Extension extension = new Extension();
            extension.setName(name);
            extension.setPath(dir.getAbsolutePath());
            return extension;
        }

        /**
         * 解析Extension加入到常量中
         *
         * @param extension
         * @param i
         */
        private void resolveExtension(Extension extension, int i) throws IOException {
            //判断是否要解析依赖
            boolean dependency = false;
            if (extensions.contains(extension)) {
                //若名称重复，以custom下的Extension为准

                //找到已存在的这个Extension
                Extension exist = extensions.parallelStream().filter(ex -> ex.getName().equals(extension.getName())).findAny().get();
                if (extension.getPath().contains("custom") && !exist.getPath().equals(extension.getPath())) {
                    //删除掉原来的元素
                    extensions.remove(extension);
                    extensions.add(extension);
                    //重新加入的Extension要解析
                    dependency = true;
                }
            } else {
                extensions.add(extension);
                //未加入过的Extension要解析
                dependency = true;
            }
            //已经加入过的Extension不解析
            if (dependency) {
                resolveDependency(extension,i);
            }

        }

        /**
         * 解析extension的依赖注册到scanner中
         *
         * @param extension
         * @param i
         */
        private void resolveDependency(Extension extension, int i) {
            //扫描Extension下的依赖
            SAXReader reader = new SAXReader();
            try {
                Document document = reader.read(extension.getPath().concat("/extensioninfo.xml"));
                List<Element> list = document.selectNodes("//requires-extension");
                for (Element element : list) {
                    this.registerDependency(element.attribute("name").getValue(),i);
                }
                //若开启了hmc 系统会自动把所有hmc的工程加入到extension中
                //扫描工程对应的hmc工程
                if (hmcEnable)
                this.registerDependency(extension.getName()+"hmc",i);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }


    }
}
