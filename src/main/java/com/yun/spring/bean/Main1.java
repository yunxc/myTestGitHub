package com.yun.spring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 * @date 2021/1/10 19:31
 */
public class Main1 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springBean.xml");
        //得到类的实例
        A a = context.getBean(A.class);
        System.out.println(a.getName());

    }

}
