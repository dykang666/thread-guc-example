package com.example.threadgucexample.geek.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: Condition.await() 同样会释放锁
 * // barrier 实现是依赖于Condition条件队列，condition 条件队列必须依赖lock才能使用
 *
 *
 * CyclicBarrier 底层依赖于 Condition
 *
 * 和CountDownLatch的区别
 *  await()：阻塞等待所有子线程到位
 * 不同点

 * @date 2024/6/28 15:37
 */
public class Condition2 {
    //A 1  B 2  C 3
    static class ShareResource {
        private int number = 1;
        private Lock lock = new ReentrantLock();
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();
        private Condition c3 = lock.newCondition();

        public void print5() {
            lock.lock();
            try {
                //1.判断
                while (number != 1) {
                    c1.await();
                }
                //2.干活
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }
                //通知
                number = 2;
                c2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void print10() {
            lock.lock();
            try {
                //1.判断
                while (number != 2) {
                    c2.await();//释放锁
                }
                //2.干活
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }
                //通知
                number = 3;
                c3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void print15() {
            lock.lock();
            try {
                //1.判断
                while (number != 3) {
                    c3.await();
                }
                //2.干活
                for (int i = 1; i <= 15; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }
                //通知
                number = 1;
                c1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        }, "CC").start();
    }




}