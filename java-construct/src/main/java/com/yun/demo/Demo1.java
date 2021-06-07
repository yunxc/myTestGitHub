package com.yun.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * @date 2021/6/1 15:39
 */
@Slf4j
public class Demo1 {


    public static void main(String[] args) throws InterruptedException {
        t1();
        //t2();
    }

    private static void t2() {

    }

    public static void t1(){
        Thread t1 = new Thread(
                ()->{
                    log.debug("开始");
                    sleep(1);
                    log.debug("结束");
                }
                , "t1");
        t1.setDaemon(true);
        t1.start();
        //t1.interrupt();
        //t1.join();
        log.debug("结束");
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
