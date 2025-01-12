package com.example.threadguc.example.geek.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 *
 * /*
 *     根据可重入锁绘制AQS
 *     AQS = CAS+CLH双向队列+fifo+LockSupport
 *        +------+  prev +------+       +------+
 *        |      | <---- |      | <---- |      |
 *  head  | node |  next | node |       | node |  tail
 *        |      | ----> |      | ----> |      |
 *        +------+       +------+       +------+
 *
 *
 * @date 2024/6/29 10:11
 */
public class AbstractQueuedSynchronizerDemo   extends Thread{

    private Lock lock;
    public AbstractQueuedSynchronizerDemo(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }

    public void run () {
        lock.lock();
        try {
            System.out.println(Thread.currentThread() + " running");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        AbstractQueuedSynchronizerDemo t1 = new AbstractQueuedSynchronizerDemo("t1", lock);
        AbstractQueuedSynchronizerDemo t2 = new AbstractQueuedSynchronizerDemo("t2", lock);
        t1.start();
        t2.start();



    }




}
