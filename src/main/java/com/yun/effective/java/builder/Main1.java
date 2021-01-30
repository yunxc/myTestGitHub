package com.yun.effective.java.builder;

import java.util.EnumMap;

/**
 * @author Administrator
 * @date 2020/11/30 10:59
 */
public class Main1 {

    public static void main(String[] args) {
        //调用方式
        User user = new User.Builder("test",18).address("test").phoneNumber("test").build();
        EnumMap em = new EnumMap(String.class);
    }
}
