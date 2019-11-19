package com.yun.word.ftl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import sun.misc.BASE64Encoder;

/**
 * @author Administrator
 * @date 2019/9/19 20:33
 */
public class DocumentHandler {

    private Configuration configuration = null;

    public DocumentHandler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    public void createDoc() throws IOException {
        // 要填入模本的数据文件
        Map dataMap = new HashMap();
        getData(dataMap);
        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        // 这里我们的模板是放在com.ftl包下面
        //configuration.setClassForTemplateLoading(this.getClass(),"/com/yun/word/ftl");
        configuration.setDirectoryForTemplateLoading(new File("D:\\workspace2\\myTestGitHub\\src\\main\\java\\com\\yun\\word\\ftl"));
        Template t = null;
        // 输出文档路径及名称
        File outFile = new File("G:\\wordtest\\ggg.doc");
        Writer out = null;

        try {
            // test.ftl为要装载的模板
            t = configuration.getTemplate("temple.ftl");
            t.setEncoding("utf-8");

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
            t.process(dataMap, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注意dataMap里存放的数据Key值要与模板中的参数相对应
     * @param dataMap
     *
     */
    @SuppressWarnings("unchecked")
    private void getData(Map dataMap) {
        //       dataMap.put("image", getImageStr());
        dataMap.put("name", "张三");

        List<Map<String, Object>> newsList=new ArrayList<Map<String,Object>>();
        for(int i=1;i<=10;i++){
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("purchaseTime", "进货日期"+i);
            map.put("product", "产品名称"+i);
            map.put("factory", "生产厂家"+i);
            map.put("spec", "产品规格"+i);
            map.put("number", "进货数量"+i);
            newsList.add(map);
        }
        dataMap.put("array",newsList);
    }
    //图片转为字符串
    private String getImageStr() {
        String imgFile = "d:/1.png";
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
