package com.example.threadgucexample.geek.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * ReentrantLock 性能低 互斥
 * ReentrantReadWriteLock
 * 读-读不互斥：读读之间不阻塞
 * 读-写互斥：读会阻塞写，写也会阻塞读
 * 写-写互斥：写写阻塞
 * @date 2024/6/29 11:06
 */
public class ReentrantLock1  extends Thread{
    private Lock lock;
    public ReentrantLock1(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }

    public void run () {
        lock.lock();
        try {
            System.out.println(Thread.currentThread() + " running");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        ReentrantLock1 t1 = new ReentrantLock1("t1", lock);
        ReentrantLock1 t2 = new ReentrantLock1("t2", lock);
        ReentrantLock1 t3 = new ReentrantLock1("t3", lock);
        t1.start();
        t2.start();
        t3.start();
    }



}
