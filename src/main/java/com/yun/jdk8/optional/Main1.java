package com.yun.jdk8.optional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020/11/6 18:52
 */
public class Main1 {
    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("公众号", "Java3y");
        hashMap.put("交流群", "回复1");

        // 使用增强for的方式来遍历hashMap
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        // 使用Lambda表达式的方式来遍历hashMap
        hashMap.forEach((s, s2) -> System.out.println(s + ":" + s2));
        hashMap.forEach((k,v) -> System.out.println("key " + k + ":value " + v));
    }
}
