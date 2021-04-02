package com.yun.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author Administrator
 * @date 2021/1/31 18:29
 */
public class NewInstanceTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, InvocationTargetException {
        Class c = Date.class;
        Date date1 = (Date) c.newInstance();    // 第1种方式：使用Class对象的newInstance()方法来创建Class对象对应类的实例
        System.out.println(date1);      // Wed Dec 19 22:57:16 CST 2018

        long timestamp =date1.getTime();
        Constructor constructor = c.getConstructor(long.class);
        Date date2 = (Date)constructor.newInstance(timestamp);  // 第2种方式：先通过Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance()方法来创建实例
        System.out.println(date2);  // Wed Dec 19 22:57:16 CST 2018


        
    }
}
