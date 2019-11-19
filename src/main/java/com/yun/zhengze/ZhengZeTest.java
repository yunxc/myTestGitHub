package com.yun.zhengze;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yunlong.zhang
 * @date 2019/1/8 19:47
 */
public class ZhengZeTest {

    public static void main(String[] args) {
        //test1();
        boolean numeric = isNumericzidai("1.02156");
        System.out.println(numeric);
    }

    public static boolean isNumericzidai(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
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

    public static void test1(){
        String line="hsiroServerUrlPrefix=http://172.16.9.60:9996/drgsms/dd";
        String ss = "(http|https)://(.*?):(.*?)(/)(.*)";
        ss = "";
        String[] split = line.split(":");
        String split1 = split[split.length - 1].split("/")[1];
        for(String s:split){
            System.out.println(s);
        }


        Pattern r = Pattern.compile(ss);
        // 现在创建 matcher 对象
        Matcher mc = r.matcher(line);
        while(mc.find()){
            System.out.println(mc.group()+"**************");
            System.out.println(mc.group(0)+"*******1*******");
        }
    }
}
