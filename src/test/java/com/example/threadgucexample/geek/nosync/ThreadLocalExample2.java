package com.example.threadgucexample.geek.nosync;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/7/3 14:59
 */
public class ThreadLocalExample2 {
    public static final Integer SIZE = 500;
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5, 5, 1,
            TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    static class LocalVariable {//总共有5M
        private byte[] locla = new byte[1024 * 1024 * 5];
    }
    static ThreadLocal<LocalVariable> local = new ThreadLocal<>();
    public static void main(String[] args) {
        try {
            for (int i = 0; i < SIZE; i++) {
                executor.execute(() -> {
                    local.set(new LocalVariable());
                    System.out.println("开始执行");
                });
                Thread.sleep(100);
            }
            local = null;//这里设置为null，依旧会造成内存泄漏
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
