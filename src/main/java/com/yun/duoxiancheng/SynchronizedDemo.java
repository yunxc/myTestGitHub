package com.yun.duoxiancheng;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @date 2020/11/26 16:40
 */
public class SynchronizedDemo implements Runnable {
    Lock lock = new ReentrantLock();
    private static volatile int count = 0;
    //以原子更新的方式更新Integer
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static int li = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SynchronizedDemo());
            thread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + count);
        System.out.println("result---li: " + li);
    }
    @Override
    public void run() {
        //synchronized (SynchronizedDemo.class){
            for(int i = 0; i < 1000000; i++)
            {
                count++;
                li = atomicInteger.addAndGet(1);
            }
        //}
    }
}