package com.yun.jdk8.stream;

import com.google.common.collect.ImmutableMap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @date 2021/1/16 19:50
 */
public class Main2 {
    public static void main(String[] args) {
        t1();
    }

    public static void t1(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("name", "a");
        m1.put("age", 15.6);

        Map<String, Object> m2 = new HashMap<>();
        m2.put("name", "b");
        m2.put("age", null);

        Map<String, Object> m3 = new HashMap<>();
        m3.put("name", "c");
        m3.put("age", 18);

        list.add(m1);
        list.add(m2);
        list.add(m3);


        List<Map<String, Object>> age = list.stream()
                .filter(map -> map.get("age") != null)
                .map(map -> {
            map.putIfAbsent("age", Integer.MAX_VALUE);
            return map;
        }).sorted(Comparator.comparingInt(o -> (int) Double.parseDouble(o.get("age").toString())))
                .collect(Collectors.toList());
        System.out.println(age);

        List<String> li = new ArrayList<>();
        li.add("a");
        li.add("b");
        li.add("c");
        StringJoiner sj = new StringJoiner(",");
        for(String s:li){
            sj.add(s);
        }
        System.out.println(sj);

    }
}
