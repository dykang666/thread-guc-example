package com.example.threadgucexample.geek.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: CyclicBarrier 基于 Condition 来实现的
 * 可以用于多线程计算数据，最后合并计算结果的场景。
 * @date 2024/6/28 15:02
 */
public class CyclicBarrierDemo2 {
    static class TaskThread extends Thread {
        CyclicBarrier barrier;
        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 到达栅栏 A");
                //阻塞等待所有线程到位
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 A");
                Thread.sleep(2000);
                System.out.println(getName() + " 到达栅栏 B");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        int N = 5;
        CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
            }
        });
        for (int i = 0; i < N; i++) {
            new TaskThread(barrier).start();
        }
    }
}
