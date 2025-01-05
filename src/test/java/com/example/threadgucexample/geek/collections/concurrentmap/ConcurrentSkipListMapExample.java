package com.example.threadgucexample.geek.collections.concurrentmap;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * cas  操作
 * @date 2025/1/1 18:39
 */
public class ConcurrentSkipListMapExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个 ConcurrentSkipListMap
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();

        // 启动多个线程，模拟并发操作
        Thread writerThread1 = new Thread(new Writer(map, 1, 10));  // 向 map 中写入 1 到 10
        Thread writerThread2 = new Thread(new Writer(map, 11, 20)); // 向 map 中写入 11 到 20
        Thread readerThread = new Thread(new Reader(map));          // 读取 map 中的内容

        writerThread1.start();
        writerThread2.start();
        readerThread.start();

        // 等待线程完成
        writerThread1.join();
        writerThread2.join();
        readerThread.join();
    }

    // 写线程：向 map 中写入数据
    static class Writer implements Runnable {
        private ConcurrentSkipListMap<Integer, String> map;
        private int start, end;

        public Writer(ConcurrentSkipListMap<Integer, String> map, int start, int end) {
            this.map = map;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                map.put(i, "Value " + i);
                System.out.println("线程 " + Thread.currentThread().getName() + " 写入: " + i + " -> Value " + i);
                try {
                    Thread.sleep(100); // 模拟延时
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // 读线程：读取 map 中的内容
    static class Reader implements Runnable {
        private ConcurrentSkipListMap<Integer, String> map;

        public Reader(ConcurrentSkipListMap<Integer, String> map) {
            this.map = map;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500); // 等待写线程先进行一些写入操作
                System.out.println("读取线程开始读取 map:");
                map.forEach((key, value) -> {
                    System.out.println("读取: " + key + " -> " + value);
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
