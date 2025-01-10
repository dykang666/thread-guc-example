package com.example.threadgucexample.geek.synronized;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/7/3 12:15
 */
public class SynchronizedObjectLock3 implements Runnable{
    static SynchronizedObjectLock3 instance1 = new SynchronizedObjectLock3();
    static SynchronizedObjectLock3 instance2 = new SynchronizedObjectLock3();

    @Override
    public void run() {
        // 所有线程需要的锁都是同一把
        synchronized(SynchronizedObjectLock3.class){
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
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
    }

}
