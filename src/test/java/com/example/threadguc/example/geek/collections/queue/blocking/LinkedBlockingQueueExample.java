package com.example.threadguc.example.geek.collections.queue.blocking;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: 适用于不确定队列大小或者需要动态调整队列容量的场景
 *      底层实现是 链表 + 锁(ReentrantLock的Condition) + 原子性
 * @date 2025/1/1 17:41
 */
public class LinkedBlockingQueueExample {
    private  static final int CAPACITY = 10;
    private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(CAPACITY);
    public static void main(String[] args) {

// 创建生产者线程
        Thread producer = new Thread(new Producer(queue));
        // 创建消费者线程
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();
    }

    // 生产者类
    static class Producer implements Runnable {
        private LinkedBlockingQueue<Integer> queue;
        public Producer(LinkedBlockingQueue<Integer> queue) {
            this.queue = queue;
        }
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    // 模拟生产
                    System.out.println("生产者生产: " + i);
                    queue.put(i);  // put 方法会阻塞直到队列有空间
                    Thread.sleep(500);  // 模拟生产时间
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
    // 消费者类
    static class Consumer implements Runnable {
        private LinkedBlockingQueue<Integer> queue;

        public Consumer(LinkedBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    // 模拟消费
                    Integer item = queue.take();  // take 方法会阻塞直到队列有数据
                    System.out.println("消费者消费: " + item);
                    Thread.sleep(1000);  // 模拟消费时间
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


}
