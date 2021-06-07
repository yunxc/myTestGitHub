package com.yun.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @date 2021/6/1 20:47
 */
@Slf4j
public class Demo3 {

   final static Object o = new Object();

    public static void main(String[] args) {
        new Thread(
                () -> {
                    synchronized(o){
                        log.info("执行。。。");
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("其他代码。。。");
                    }
                }
        ).start();

        new Thread(
                () -> {
                    synchronized(o){
                        log.info("执行。。。");
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("其他代码。。。");
                    }
                }
        ).start();

        TimeUtil.sleep(2);
        log.info("唤醒 o 上的其他线程");
        synchronized(o){
            o.notifyAll();
        }
    }

}
