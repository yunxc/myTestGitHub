package com.jdk8.jc;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xmx10m -Xms10m -XX:+PrintGC -XX:+PrintGCDetails
 * @author Administrator
 * @date 2021/5/11 9:39
 */
public class TestGC {

    private static  final  int _1MB = 1024*1024;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        List<byte[]> li = new ArrayList<>();
        while(true){
            byte[] bytes = new byte[_1MB];
            li.add(bytes);
            System.out.println(++i);
            if(i % 10 == 0){
                li.clear();
            }
            Thread.sleep(500);
        }
    }

}
