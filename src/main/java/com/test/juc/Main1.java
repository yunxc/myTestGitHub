package com.test.juc;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @date 2021/3/25 20:53
 */
public class Main1 {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger a = new AtomicInteger();
        int i = a.addAndGet(1);
        System.out.println(i);
        Lock lock = new ReentrantLock();

        Set<String> set = new HashSet<>();
        set.add("");

    }

}
