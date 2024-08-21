package com.example.threadgucexample.geek.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:  经典场景 数据库连接池 、停车场场景 这种限制数量的锁
 * @date 2024/6/28 16:43
 */
public class SemaphoreDemo1 {
    public static void main(String[] args) {
        /*
         * Semaphore semaphore = new Semaphore(3);//最多支持多少个线程 for (int i = 1; i <= 10;
         * i++) { new User(semaphore, i + "线程").start(); }
         */
        // 有3个车位当占满时，semaphore为0，当走了一个车时，
        // semaphore.release()则加1释放出来一个车位

        Semaphore semaphore = new Semaphore(3);// 型号灯,值是伸缩的，用满了0，空出来加，模拟3个停车位
        for (int i = 1; i <= 10; i++) {// 模拟10部汽车
            new Thread(() -> {
                try {
                    semaphore.acquire();// 占到车位
                    System.out.println(Thread.currentThread().getName() + "\t 抢占到车位");
                    TimeUnit.SECONDS.sleep(3);// 停3秒，后出去
                    System.out.println(Thread.currentThread().getName() + "\t 停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();// 释放停车位
                }
            }, String.valueOf(i)).start();
        }
    }

}
