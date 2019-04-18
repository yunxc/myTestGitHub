package com.yun.main;

import java.util.UUID;

/**
 * @author z
 * @date 2019/3/26 11:26
 */
public class Main1 {

    public static void main(String[] args) {
        while (true){
            System.out.println("uuid = " + uuid());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
