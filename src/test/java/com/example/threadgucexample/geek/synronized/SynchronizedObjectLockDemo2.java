package com.example.threadgucexample.geek.synronized;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/7/2 9:57
 */
public class SynchronizedObjectLockDemo2 implements Runnable {

    static SynchronizedObjectLockDemo2 instance = new SynchronizedObjectLockDemo2();

    @Override
    public void run() {
        method();
    }
    //指synchronize修饰静态的方法或指定锁对象为Class对象
    public synchronized void method() {
        System.out.println("我是线程" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束");
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
    }
}
