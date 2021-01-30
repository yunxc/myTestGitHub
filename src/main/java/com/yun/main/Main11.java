package com.yun.main;

import java.io.File;

/**
 * @author Administrator
 * @date 2020/9/29 16:34
 */
public class Main11 {
    public static void main(String[] args) {
        String rootPath = "E:\\A病案质检\\A规则文件Excel\\20201026\\广东地区\\解密\\RU001.xlsx";
        File file = new File(rootPath);
        System.out.println(file.getParent());
        System.out.println(file.getPath());

        String i = "3725";
        System.out.println(i.substring(2));
    }
}
