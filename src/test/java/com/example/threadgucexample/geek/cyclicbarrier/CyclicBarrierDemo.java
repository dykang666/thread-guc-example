package com.example.threadgucexample.geek.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author kangdongyang
 * @version 1.0
 * 循环屏障
 * @description: 利用CyclicBarrier类可以实现一组线程相互等待，当所有线程都到达某个屏障点后再进行后续的操作。下图演示了这一过程。
 * https://www.cnblogs.com/crazymakercircle/p/13906379.html
CyclicBarrier的await方法是使用ReentrantLock和Condition控制实现的，使用的Condition实现类是ConditionObject，它里面有一个等待队列和await方法，这个await方法会向队列中加入元素。当调用CyclicBarrier的await方法会间接调用ConditionObject的await方法，当屏障关闭后首先执行指定的barrierAction，然后依次执行等待队列中的任务，有先后顺序。
 * // 线程挂起实现使用的 condition 队列，当前代所有线程到位，这个条件队列内的线程才会被唤醒
 *
 * @date 2024/6/28 12:16
 */
public class CyclicBarrierDemo {

    public static class Soldier implements Runnable{
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }
        /**
         * CyclicBarrier的核心方法就只有一个await，它会抛出两个异常，InterruptedException和BrokenBarrierException。
         * InterruptedException显然是当前线程等待的过程被中断而抛出的异常，而这些要集合的线程有一个线程被中断就会导致线程永远都无法集齐，
         * 导致“栅栏损坏”，剩下的线程就会抛出BrokenBarrierException异常
         */
        @Override
        public void run() {
            try {
                //等待士兵到齐
                cyclicBarrier.await();
                //士兵开始做各自的工作
                doWork();
                //等待所有士兵完成任务
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }


        }
        void doWork(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + " 任务完成");
        }
    }
    /**
     * 这个类用于barrierAction
     */
    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;
        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }
        @Override
        public void run() {
            if ( flag ){
                System.out.println("司令：[士兵" + N + "个, 任务完成!]");
            } else {
                System.out.println("司令：[士兵" + N + "个，集合完毕!]");
                flag = true;
            }
        }
    }
    //CyclicBarrier 不会阻塞主线程，只会阻塞子线程

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        for (int i = 0; i < N; i++) {
            allSoldier[i] = new Thread(new Soldier("士兵" + i, cyclicBarrier));
            allSoldier[i].start();
//            /**
//             * 测试 栅栏损坏情况
//             *
//             * 得到1个InterruptedException和9个BrokenBarrierException
//             */
//            if ( i == 5 ){
//                allSoldier[0].interrupt();
//            }
        }




    }
}
