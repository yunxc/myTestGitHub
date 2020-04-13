package com.yun.main;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Administrator
 * @date 2020/1/15 11:07
 */
public class Main7 {
    public static void main(String[] args) {
        Map<String, Object> m1 = new HashMap<>();
        m1.put("a","jodkj");
        System.out.println((String) m1.get("b"));

        StringJoiner sj = new StringJoiner(",");
        sj.add("q");
        sj.add("q");
        sj.add("q");
        System.out.println(sj);

    }
}
