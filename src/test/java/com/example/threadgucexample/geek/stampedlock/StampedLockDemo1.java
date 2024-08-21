package com.example.threadgucexample.geek.stampedlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.StampedLock;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: 不支持重入
 *
 * ReadWriteLock支持两种访问模式：读锁和写锁
 * StampedLock  读写锁的性能之王   印花锁   悲观读锁可以升级为写锁
 * 支持三种访问模式：写锁、悲观读锁和乐观读
 * @date 2024/6/29 18:25
 */
@Slf4j
public class StampedLockDemo1 {
    private static final StampedLock stampedLock = new StampedLock();
    private  static double x=100;
    private static double y=200;
    public static void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock(); // 获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp); // 释放写锁
        }
    }
    public static void main(String[] args) {
        long stamp = stampedLock.tryOptimisticRead(); // 获得一个乐观读锁
// 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
        double currentX = x;
        // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        double currentY = y;
        // 此处已读取到y，如果没有写入，读取是正确的(100,200)
        // 如果有写入，读取是错误的(100,400)
        move(100, 100);
        if (!stampedLock.validate(stamp)) { // 检查乐观读锁后是否有其他写锁发生
            stamp = stampedLock.readLock(); // 获取一个悲观读锁
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp); // 释放悲观读锁
            }
        }
         log.info("currentX:{},currentY:{}",currentX,currentY);

    }
}
