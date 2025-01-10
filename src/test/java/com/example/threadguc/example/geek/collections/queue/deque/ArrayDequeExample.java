package com.example.threadguc.example.geek.collections.queue.deque;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 *  Deque双端队列 允许在队列的两端进行增删操作 先进先出（FIFO） 后进先出（LIFO）
 *  缓存：Deque可以用作缓存，例如在LRU（最近最少使用）缓存算法中，Deque可以用来存储最近使用的元素。
 * @date 2025/1/1 18:22
 *   ReentrantLock 和 Condition
 * Stack 只能先进后出  入栈和出栈
 */
public class ArrayDequeExample {
    public static void main(String[] args) {
        // 创建一个双端队列（Deque）
        Deque<Integer> deque = new ArrayDeque<>();

        // 示例 1: 作为队列使用 (FIFO)
        System.out.println("队列示例 (FIFO):");
        deque.addLast(1); // 添加元素到队尾
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        System.out.println("当前队列: " + deque);

        System.out.println("从队头移除: " + deque.removeFirst()); // 移除队头元素
        System.out.println("从队头移除: " + deque.removeFirst());
        System.out.println("当前队列: " + deque);

        // 示例 2: 作为栈使用 (LIFO)
        System.out.println("\n栈示例 (LIFO):");
        deque.addFirst(5); // 添加元素到队头
        deque.addFirst(6);
        deque.addFirst(7);

        System.out.println("当前栈: " + deque);

        System.out.println("从栈顶移除: " + deque.removeFirst()); // 移除栈顶元素
        System.out.println("从栈顶移除: " + deque.removeFirst());
        System.out.println("当前栈: " + deque);
    }
}
