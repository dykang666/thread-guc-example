package com.example.threadgucexample.geek.nosync;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: 线程本地存储
 * ThreadLocal 从理论上讲并不是用来解决多线程并发问题的，因为根本不存在多线程竞争。
 * @date 2024/7/3 13:19
 */
public class ThreadLocalExample1 {
    public static void main(String[] args) {
        ThreadLocal threadLocal1 = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();
        Thread thread1 = new Thread(() -> {
            threadLocal1.set(1);
            threadLocal2.set(1);
        });
        Thread thread2 = new Thread(() -> {
            threadLocal1.set(2);
            threadLocal2.set(2);
        });
//        System.out.println(threadLocal1.get());
//        System.out.println(threadLocal2.get());
        thread1.start();
        thread2.start();
    }
}
