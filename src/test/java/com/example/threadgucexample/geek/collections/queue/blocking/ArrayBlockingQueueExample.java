package com.example.threadgucexample.geek.collections.queue.blocking;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * Blocking 阻塞的意思，当队列已满或为空时，线程会被阻塞，等待队列有空位或数据可用。
 * 有界阻塞队列
 *    底层实现是数组 + 锁 （ReentrantLock）+    生产者消费者模型（最适合）
 * @date 2024/12/31 21:26
 */
public class ArrayBlockingQueueExample {
    public static void main(String[] args) {
        // 创建一个容量为 10 的 ArrayBlockingQueue
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        // 生产者线程：将数据放入队列
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i++) {
                    queue.put(i); // 如果队列已满，生产者会阻塞
                    System.out.println("Produced: " + i);
                    Thread.sleep(100); // 模拟生产延迟
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 消费者线程：从队列取出数据
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Integer item = queue.take(); // 如果队列为空，消费者会阻塞
                    System.out.println("Consumed: " + item);
                    Thread.sleep(200); // 模拟消费延迟
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 启动生产者和消费者线程
        producer.start();
        consumer.start();
    }

}
