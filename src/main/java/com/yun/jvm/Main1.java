package com.yun.jvm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/1/25 10:52
 */
public class Main1 {
    public static void main(String[] args) throws InterruptedException {

        Map<Double, Object> m1 = new HashMap<>();
        Map<String, Object> m2 = new HashMap<>();
        Map<String, Object> m3 = new HashMap<>();
        double i = 1.0;
        for(;;){
            Thread.sleep(10);
            Map<String, String> m = new HashMap<>();
            m.put("abc1", "abc");
            m.put("abc2", "abc");
            m.put("abc3", "abc");
            m1.put(i, m);
            m3.put(("AAAAA-"+(i)), m);
            System.out.println(i++);
        }

    }
}
