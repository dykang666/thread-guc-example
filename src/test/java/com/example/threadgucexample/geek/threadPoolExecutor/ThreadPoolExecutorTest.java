package com.example.threadgucexample.geek.threadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * ThreadPoolExecutor本质上是一个生产者 - 消费者模式的实现，内部有一个任务队列，这个任务队列是生产者和消费者通信的媒介；
 * ThreadPoolExecutor 可以有多个工作线程，但是这些工作线程都共享一个任务队列
 * @date 2024/7/1 16:51
 */
@Slf4j
public class ThreadPoolExecutorTest {

    private static final int taskCount = 50;//任务数

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,//核心线程数
                20,//最大线程数
                5,//非核心回收超时时间
                TimeUnit.SECONDS,//超时时间单位
                new ArrayBlockingQueue<>(30));//任务队列
        System.out.println("总任务数：" + taskCount);
        long start = System.currentTimeMillis();
        //模拟任务提交
        for (int i = 0; i < taskCount; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在执行第" + integer.incrementAndGet() + "个任务");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        long end = 0;

        while (executor.getCompletedTaskCount() < 50) {
            end = System.currentTimeMillis();
        }
        System.out.println("任务总耗时：" + (end - start));
    }

}
