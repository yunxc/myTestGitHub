package com.yun.duoxiancheng;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @date 2020/11/26 20:17
 */
public class LockDemo {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Map m1 = new HashMap();
        List li = new ArrayList();
        Hashtable hashtable = new Hashtable();
        ConcurrentHashMap chp = new ConcurrentHashMap();
        List list = Collections.synchronizedList(li);
        ConcurrentLinkedQueue cq = new ConcurrentLinkedQueue();
        ThreadLocal tl = new ThreadLocal();
        ArrayBlockingQueue abq = new ArrayBlockingQueue(8);
        ThreadPoolExecutor te = new ThreadPoolExecutor(1,1,2, TimeUnit.SECONDS, null);
        te.execute(null);
        FutureTask futureTask = new FutureTask(null);
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.addAndGet(1);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
    }

    }
