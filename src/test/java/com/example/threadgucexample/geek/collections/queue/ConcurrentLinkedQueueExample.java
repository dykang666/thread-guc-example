package com.example.threadgucexample.geek.collections.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * 单向链表实现的无界队列
 * 使用场景 生产者-消费者模式  任务队列  消息队列  缓存清理和淘汰机制   线程池中的任务调度
 *   非阻塞的线程安全队列
 *   无界线程安全队列  先进先出
 *   单向链表实现的无界队列
 *   底层cas + volatile       无锁  适合高并发场景
 * @date 2024/12/31 18:17
 */
public class ConcurrentLinkedQueueExample {
    public static void main(String[] args) {
        // 创建一个 ConcurrentLinkedQueue 实例
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        // 创建并启动一个生产者线程，模拟生产者将元素添加到队列中
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                String item = "Item " + i;
                queue.offer(item);  // 将元素加入队列
                System.out.println("Produced: " + item);
                try {
                    Thread.sleep(500);  // 模拟生产过程的延迟
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 创建并启动一个消费者线程，模拟消费者从队列中取出元素
        Thread consumer = new Thread(() -> {
            while (true) {
                String item = queue.poll();  // 从队列中取出元素
                if (item != null) {
                    System.out.println("Consumed: " + item);
                } else {
                    System.out.println("Queue is empty, waiting for new items...");
                }
                try {
                    Thread.sleep(1000);  // 模拟消费过程的延迟
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 启动生产者和消费者线程
        producer.start();
        consumer.start();
    }
}
