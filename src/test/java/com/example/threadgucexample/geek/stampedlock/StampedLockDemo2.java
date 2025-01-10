package com.example.threadgucexample.geek.stampedlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: 印花锁
 * @date 2024/6/29 18:49
 */
public class StampedLockDemo2 {
    final static StampedLock lock = new StampedLock();
    final static List<Long> list = new ArrayList<Long>();
    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //写任务
        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                    write();
            }
        };
        //读任务
        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                    read();
            }
        };
        //提交9个读任务，1个写任务
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(writeTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.shutdown();
    }



    public static void write() {
        long stamped = -1;
        try {
            //获取写锁
            stamped = lock.writeLock();
            long current = System.currentTimeMillis();
            list.add(current);
            System.out.println("W-> "+current);
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放写锁
            lock.unlockWrite(stamped);
        }
    }
    public static void read() {
        //获取乐观锁，并返回stamp值，该方法不会使writeLock阻塞
        long stamp =lock.tryOptimisticRead();
        //读取数据
        System.out.println("normal R"+Thread.currentThread().getName());
        System.out.println("normal R-> "+stamp+"=="+list.size());
        //判断stamp值是否发生变化
        if (!lock.validate(stamp)) {
            //内容被修改，重新获取
            try {
                stamp = lock.readLock();
                System.out.println("data change R-> "+stamp+"=="+list.size());
                System.out.println("data change R"+Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamp);
            }
        }
    }



}
