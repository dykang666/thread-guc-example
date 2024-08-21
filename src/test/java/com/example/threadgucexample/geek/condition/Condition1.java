package com.example.threadgucexample.geek.condition;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 *  * // barrier 实现是依赖于Condition条件队列，condition 条件队列必须依赖lock才能使用
 *  // 线程挂起实现使用的 condition 队列，当前代所有线程到位，这个条件队列内的线程才会被唤醒
 * @date 2024/6/28 12:33
 * 如果说Lock用来替代synchronized，那么Condition就是用来替代Object.wait()/notify()。
 * Conditon中的await()对应Object的wait()，Condition中的signal()对应Object的notify()；
 */
@Slf4j
public class Condition1 implements  Runnable{
    //可重入锁
    public static ReentrantLock reentrantLock = new ReentrantLock();
    public static Condition condition = reentrantLock.newCondition();

    @Override
    public void run() {
        reentrantLock.lock();
        try {
            System.out.println("我来了2222");
            condition.await();
            System.out.println("我来了111");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Condition1 condition1 = new Condition1();
        Thread thread = new Thread(condition1);
        thread.start();
        Thread.sleep(2000);
        reentrantLock.lock();
        try {
           // condition.signal(); //需要通知
            condition.signalAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }
}
