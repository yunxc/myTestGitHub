package com.yun.zhengze;

import java.util.regex.Pattern;

/**
 * @author Administrator
 * @date 2020/11/2 14:14
 */
public class Main1 {
    public static void main(String[] args) {
        //正则表达式
        String pattern = "Z44\\.3.*";

        //内容
        String content = "Z44.300";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("匹配结果： " + isMatch);

        String ss = "nj.125||nmkmk0.155||mkm.1263";
        String[] split = ss.split("\\|\\|");
        for(int i = 0; i < split.length; i++){
            System.out.println(split[i]);
        }
    }
}
