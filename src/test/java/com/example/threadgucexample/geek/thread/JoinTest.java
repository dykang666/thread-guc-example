package com.example.threadgucexample.geek.thread;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 *  join是成员方法，当前线程调用其他线程的join方法，形成阻塞，当调用的线程运行结束时，阻塞结束。
 *  join(time)表示在设置时间内指定线程没有执行完，也结束阻塞，继续执行代码。
 *
 * @date 2024/7/3 20:24
 */
@Slf4j
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread());
        thread.start();
        System.out.println("Main join wait " + LocalTime.now());
        // 阻塞
        // 一种特殊的wait，当前运行线程调用另一个线程的join方法
        // 当前线程进入阻塞状态直到另一个线程运行结束等待该线程终止。 注意该方法也需要捕捉异常。
        //如：t.join();//主要用于等待t线程运行结束，若无此句，main则会执行完毕，导致结果不可预测。
        log.info(thread.getName());
        thread.join();
        System.out.println("Main end " + LocalTime.now());
    }
    public static class MyThread implements Runnable{
        @Override
        public void run() {
            try {
                log.info( "MyThread start " + LocalTime.now());
                Thread.sleep(2000);
                log.info(Thread.currentThread().getName());
                log.info( "MyThread end " + LocalTime.now());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
