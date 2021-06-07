package com.juc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Administrator
 * @date 2021/5/21 20:47
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        test2();
        System.out.println("main -------");
    }

    private static void test2() throws InterruptedException, ExecutionException {
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 创建存储任务的容器
        List<Callable<String>> tasks = new ArrayList<Callable<String>>();

        // 提交10个任务
        for ( int i=0; i<10; i++ ) {
            Callable<String> task = new Callable<String>(){
                @Override
                public String call() throws InterruptedException {
                    int sleepTime = new Random().nextInt(1000);
                    Thread.sleep(20);
                    return "-------===111";
                }
            };
            executorService.submit( task );
            // 将task添加进任务队列
            tasks.add( task );
        }

        // 获取10个任务的返回结果
        List<Future<String>> results = executorService.invokeAll( tasks );

        // 输出结果
        for ( int i=0; i<10; i++ ) {
            // 获取包含返回结果的future对象
            Future<String> future = results.get(i);
            // 从future中取出执行结果（若尚未返回结果，则get方法被阻塞，直到结果被返回为止）
            String result = future.get();
            System.out.println(result);
        }
        System.out.println("end");
    }

    private static void test1() throws Exception {
        Integer sum = 0;
        ExecutorService exec = Executors.newFixedThreadPool(10);
        final BlockingQueue<Future<Integer>> queue = new LinkedBlockingDeque<Future<Integer>>(
                10);
        //实例化CompletionService
        final CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
                exec, queue);
// 提交10个任务
        for ( int i=0; i<10; i++ ) {
            exec.submit( new Callable<Integer>(){
                @Override
                public Integer call() throws InterruptedException {
                    // Thread.sleep(200);
                    return 1;
                }
            } );
            System.out.println("====================000000000000");
        }
        System.out.println("====================111111111111");
        // 输出结果
        for ( int i=0; i<10; i++ ) {
            // 获取包含返回结果的future对象（若整个阻塞队列中还没有一条线程返回结果，那么调用take将会被阻塞，当然你可以调用poll，不会被阻塞，若没有结果会返回null，poll和take返回正确的结果后会将该结果从队列中删除）
            Future<Integer> future = completionService.take();
            // 从future中取出执行结果，这里存储的future已经拥有执行结果，get不会被阻塞
            Integer result = future.get();
            sum = sum + result;
        }
        System.out.println(sum);
    }

}
