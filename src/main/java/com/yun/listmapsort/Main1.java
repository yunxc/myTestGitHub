package com.yun.listmapsort;

import java.time.YearMonth;
import java.util.*;

/**
 * @author Administrator
 * @date 2019/6/1 17:22
 */
public class Main1 {

    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("a",1);
        m1.put("b",50);
        m1.put("c","b");

        Map<String, Object> m2 = new HashMap<String, Object>();
        m2.put("a",15);
        m2.put("b",5);
        m2.put("c","b");

        Map<String, Object> m3 = new HashMap<String, Object>();
        m3.put("a",6);
        m3.put("b",10);
        m3.put("c","b");

        list.add(m1);
        list.add(m2);
        list.add(m3);

        Map<String, Double> resMap = new HashMap<String, Double>();

        for(String key:list.get(0).keySet()){
            Object o = list.get(0).get(key);
            if(o instanceof Integer){
                String max_key = "max_" + key;
                String min_key = "min_" + key;
                resMap.put(max_key,Integer.parseInt(o.toString()) * 1.0);
                resMap.put(min_key,Integer.parseInt(o.toString()) * 1.0);
            }
        }

        for(Map<String, Object> m:list){
            for(String key:m.keySet()){
                Object o = m.get(key);
                if(o instanceof Integer){
                    int i = Integer.parseInt(o.toString());
                    resMap.put("max_" + key,Math.max(i,resMap.get("max_" + key)));
                    resMap.put("min_" + key,Math.min(i,resMap.get("min_" + key)));
                }
            }
        }
        System.out.println(resMap);

        String  a = "";
        a = a + "," + "ddddd";
        a = a + "," + "vvvvvv";
        String substring = a.substring(1);
        System.out.println(substring);
    }

}
