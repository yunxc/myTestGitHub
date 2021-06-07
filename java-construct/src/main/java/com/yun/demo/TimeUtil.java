package com.yun.demo;

/**
 * @author Administrator
 * @date 2021/6/1 20:48
 */
public class TimeUtil {

    public static void sleep(long time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
