package com.yun.demo.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @date 2021/6/4 17:57
 */
@Slf4j
public class PoolTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(2);
        for(int i = 0; i < 4; i++){
            int j = i;
            threadPool.execute(()->{
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{}", j);
            });
        }


        Thread.sleep(10000L);
        log.info("================================================================");

        for(int i = 0; i < 4; i++){
            int j = i;
            threadPool.execute(()->{
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{}", j);
            });
        }
    }

}

/**
 * 队列
 * @param <T>
 */
@Slf4j
class BlockingQueue<T>{

    private Deque<T> queue = new ArrayDeque<>();

    private ReentrantLock lock = new ReentrantLock();

    private Condition fullWaitSet = lock.newCondition();

    private Condition emptyWaitSet = lock.newCondition();

    private int capcity;

    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    /**
     * 获取
     * @return
     */
    public T take(){
        lock.lock();
        try {
            while (true){
                if(queue.isEmpty()){
                    log.info("await....");
                    emptyWaitSet.await(10, TimeUnit.SECONDS);
                } else {
                    T t = queue.removeFirst();
                    fullWaitSet.signal();
                    return t;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
       return null;
    }

    public void put(T t){
        lock.lock();
        try {
            while (true){
                if(capcity == queue.size()){
                    log.info("fullWaitSet....{}", t);
                    fullWaitSet.await();
                } else {
                    log.info("put....{}", t);
                    queue.addLast(t);
                    emptyWaitSet.signal();
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public int size(){
        lock.lock();
        try {
            return queue.size();
        }finally {
            lock.unlock();
        }
    }
}

@Slf4j
class ThreadPool{

    private BlockingQueue<Runnable> taskQueue;

    private HashSet<Worker> workers = new HashSet<>();

    private int coreSize;

    public ThreadPool(int coreSize) {
        this.coreSize = coreSize;
        this.taskQueue = new BlockingQueue<>(10);
    }

    public void execute(Runnable task){
        synchronized (workers){
            if(workers.size() < coreSize){
                log.info("workers.add...{}", task);
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else {
                log.info("taskQueue.put...{}", task);
                taskQueue.put(task);
            }
        }
    }

    class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || (task = taskQueue.take()) != null){
                try {
                    log.info("正在执行。。。{}", task);
                    task.run();
                    log.info("执行完成。。。{}", task);
                }catch (Exception e){
                    e.printStackTrace();
                } finally {
                    task = null;
                }
                synchronized (workers){
                    log.info("work 被移除{}", this);
                    workers.remove(this);
                }
            }
        }
    }

}

