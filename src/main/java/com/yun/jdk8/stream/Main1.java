package com.yun.jdk8.stream;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @date 2020/11/10 11:28
 */
public class Main1 {
    public static void main(String[] args) {
        int a = 10;
        List<Integer> b_s = new ArrayList<>();
        b_s.add(1);
        b_s.add(2);
        b_s.add(3);
        LocalDateTime now = LocalDateTime.now();
        String formatTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(formatTime);
    }

    private static void add(int a, int b){
        System.out.println("a + b = " + a+ b);
    }
}
