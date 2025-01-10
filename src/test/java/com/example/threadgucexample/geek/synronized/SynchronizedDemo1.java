package com.example.threadgucexample.geek.synronized;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: 使用 synchronized(this) 和 synchronized(.class) 的区别在于它们锁定的对象不同。
 *当使用 synchronized(this) 时，锁定的是当前对象实例，即只有一个线程可以访问该对象实例的同步代码块或方法。这意味着如果有多个对象实例，
 * 每个对象实例都有自己的同步代码块或方法，可以同时被一个线程访问。
 *
 * 当使用 synchronized(.class) 时，锁定的是类的 Class 对象，即只有一个线程可以访问该类的同步代码块或方法，不管有多少个实例。这意味着无论有多少个对象实例，
 * 它们都共享同一个 Class 对象，只有一个线程可以访问该类的同步代码块或方法。
 * @date 2024/7/2 9:19
 */
public class SynchronizedDemo1   implements Runnable{
    static SynchronizedDemo1 instance = new SynchronizedDemo1();

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("我是线程" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
    }

}
