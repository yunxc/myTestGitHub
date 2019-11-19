package com.yun.poi_tl;

import com.deepoove.poi.XWPFTemplate;

import java.io.FileOutputStream;
import java.util.HashMap;

/**
 * @author Administrator
 * @date 2019/9/20 10:22
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //核心API采用了极简设计，只需要一行代码
        XWPFTemplate template = XWPFTemplate.compile("G:\\wordtest\\tl.docx").render(new HashMap<String, Object>(){{
            put("title", "Poi-tl 模板引擎");
        }});
        FileOutputStream out = new FileOutputStream("G:\\wordtest\\out_template.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }

}
