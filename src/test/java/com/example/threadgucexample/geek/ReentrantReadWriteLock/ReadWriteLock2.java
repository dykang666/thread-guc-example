package com.example.threadgucexample.geek.ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: ReentrantReadWriteLock支持锁降级，上面代码不会产生死锁。这段代码虽然不会导致死锁，但没有正确的释放锁。
 *   从写锁降级成读锁，并不会自动释放当前线程获取的写锁，仍然需要显示的释放，否则别的线程永远也获取不到写锁
 * @date 2024/6/29 11:44
 */
public class ReadWriteLock2 {
    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        reentrantReadWriteLock.writeLock().lock();
        System.out.println("get write lock !");
        reentrantReadWriteLock.readLock().lock();
        System.out.println("get read lock !");
    }
}
