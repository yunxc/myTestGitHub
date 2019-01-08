package com.yun.zhengze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yunlong.zhang
 * @date 2019/1/8 19:47
 */
public class ZhengZeTest {

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        String line="<serviceinf><servicecode>SYPCRWHHQ</servicecode>" +
                "\n</serviceinf>";
        Pattern r = Pattern.compile("<serviceinf>([\\s\\S]*)</serviceinf>");
        // 现在创建 matcher 对象
        Matcher mc = r.matcher(line);
        while(mc.find()){
            System.out.println(mc.group()+"**************");
            System.out.println(mc.group(1)+"*********1*****");
        }
    }
}
