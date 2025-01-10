package com.example.threadgucexample.geek.collections;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 *
 * 适合读多写少的场景
 *  ReentrantLock(原子性和一致性)+volatile
 * @date 2025/1/1 18:12
 */
public class CopyOnWriteArraySetExample  {
    public static void main(String[] args) {
        // 创建一个 CopyOnWriteArraySet
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();

        // 启动多个线程，模拟并发操作
        Thread writerThread = new Thread(new Writer(set));
        Thread readerThread = new Thread(new Reader(set));

        writerThread.start();
        readerThread.start();
    }
    // 写线程：往集合中添加数据
    static class Writer implements Runnable {
        private CopyOnWriteArraySet<Integer> set;

        public Writer(CopyOnWriteArraySet<Integer> set) {
            this.set = set;
        }

        @Override
        public void run() {
            try {
                // 向集合中添加元素
                for (int i = 1; i <= 5; i++) {
                    set.add(i);
                    System.out.println("添加元素: " + i);
                    Thread.sleep(1000); // 模拟延时
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // 读线程：读取集合中的数据
    static class Reader implements Runnable {
        private CopyOnWriteArraySet<Integer> set;

        public Reader(CopyOnWriteArraySet<Integer> set) {
            this.set = set;
        }

        @Override
        public void run() {
            try {
                // 读取集合中的元素
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(500);  // 模拟延时
                    System.out.println("当前集合内容: " + set);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
