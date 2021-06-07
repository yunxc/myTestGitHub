package com.yun.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Administrator
 * @date 2021/6/1 20:57
 */
@Slf4j
public class Demo5 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("start");
            TimeUtil.sleep(2);
            log.info("park");
            LockSupport.park();
            log.info("resume");
        },"t1");
        t1.start();

        TimeUtil.sleep(1);
        log.info("unpark");
        LockSupport.unpark(t1);

        try {
            int a = 1/0;
        }finally {
            System.out.println("===========");
        }

    }

}
