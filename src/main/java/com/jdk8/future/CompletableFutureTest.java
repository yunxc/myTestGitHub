package com.jdk8.future;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Administrator
 * @date 2021/5/24 14:52
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test1();
        String substring = "mmcc".substring(0, 1);
        boolean contains = "a,b,g,m".contains(substring.toUpperCase());
        System.out.println();
    }

    private static void test1() throws ExecutionException, InterruptedException {
        List<CompletableFuture<Long>> futureList = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(3);
        CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run1 end ..."+ new Date());
            return 1L;
        });
        futureList.add(future1);
        CompletableFuture<Long> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run2 end ..."+ new Date());
            return 2L;
        });
        futureList.add(future2);
        CompletableFuture<Long> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                int m = 1/0;
            } catch (InterruptedException e) {
            }
            System.out.println("run3 end ..." + new Date());
            return 3L;
        });
        futureList.add(future3);

        /*for(int i =0; i< 50; i++){
            CompletableFuture<Long> future_ = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                System.out.println("run3 end ..." + new Date());
                return 3L;
            });
            futureList.add(future_);
        }*/

        Long sum = 0L;
        for (CompletableFuture<Long> future : futureList) {
            sum = sum + future.get();
        }
        service.shutdown();
        System.out.println("sum:" + sum + "========" + new Date());
    }

}
