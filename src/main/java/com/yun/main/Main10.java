package com.yun.main;

/**
 * @author Administrator
 * @date 2020/9/29 16:34
 */
public class Main10 {
    public static void main(String[] args) {
        String s1 = "avb12533";
        String s2 = "dfhh12533";
        String substring = s1.substring(0, 3);
        System.out.println(substring);
        System.out.println("1" == "1");
        String aa = "$(诊断1编码)（$(诊断1名称)）与$(手术1编码)（$(手术1名称)）不匹配，建议选择$(其他手术编码)（$(其他手术名称)）为主要手术操作";
        System.out.println(aa.replace("$(诊断1编码)","jjjjj"));
    }
}
