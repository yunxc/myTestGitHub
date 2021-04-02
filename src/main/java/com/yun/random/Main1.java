package com.yun.random;

import java.util.Optional;
import java.util.Random;

/**
 * @author Administrator
 * @date 2021/3/1 18:35
 */
public class Main1 {

    public static void main(String[] args) {
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            System.out.println(random.nextBoolean());
        }
        System.out.println("===================");
        System.out.println(Optional.ofNullable(2).orElse(1));
        String s = "2020-01";
        System.out.println(s.substring(0,4));

        Integer i = 5;

        Integer integer = Optional.ofNullable(i).orElse(0);
        System.out.println(integer);

        Integer integer1 = Optional.of(i).orElse(0);
        System.out.println(integer1);

    }



}
