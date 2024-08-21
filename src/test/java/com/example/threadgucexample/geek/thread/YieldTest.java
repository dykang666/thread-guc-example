package com.example.threadgucexample.geek.thread;

import java.time.LocalTime;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * 是Thread的static方法，可直接调用，表示让出CPU资源，其他线程线程和自己重新竞争执行机会，不会阻塞线程。
 *  该方法不能保证其他线程能争抢到，有可能还是自己抢到。
 * @date 2024/7/3 20:21
 */
public class YieldTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread());
        thread.start();

        Thread thread2 = new Thread(new MyThread());
        thread2.start();
    }


    public static class MyThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i + " " + LocalTime.now());
// yield()只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行。
// 调用yield方法只是一个建议，告诉线程调度器我的工作已经做的差不多了，可以让别的相同优先级的线程使用CPU了，没有任何机制保证采纳。
                Thread.yield();
                System.out.println(Thread.currentThread().getName() + " " + i + " yield " + LocalTime.now());
            }
        }
    }
}
