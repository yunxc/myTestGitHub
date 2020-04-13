package com.yun.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.time.LocalDate;

/**
 * @author Administrator
 * @date 2020/3/22 18:50
 */
public class Main9 {
    public static void main(String[] args) throws IOException {
        LocalDate follow_date = LocalDate.now().plusDays(1);
        System.out.println(follow_date.getDayOfMonth());
        System.out.println(follow_date.getMonthValue());
        System.out.println(follow_date.getYear());
    }
}
