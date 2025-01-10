package com.example.threadgucexample.geek.FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/7/4 11:48
 */
public class FutureTaskDemo1  implements Callable<String> {
    // FutureTask_1 中持有 FutureTask_2 的引用
    FutureTask<String> futureTask_2;
    // 通过构造器初始化 成员变量

    public FutureTaskDemo1(FutureTask<String> futureTask_2) {
        this.futureTask_2 = futureTask_2;
    }



    @Override
    public String call() throws Exception {
        System.out.println("T1：洗水壶");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("T1：烧开水");
       // TimeUnit.SECONDS.sleep(15);
        // 获取 T2 线程的茶叶
        String teas = futureTask_2.get();
        System.out.println("拿到茶叶：" + teas);
        System.out.println("T1：开始泡茶...");
        return "上茶：" + teas;
    }

    /**
     * FutureTask_2 需要执行的任务：洗茶壶、洗茶杯、拿茶叶
     */
    public static class FutureTask_2 implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("T2：洗茶壶");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("T2：洗茶杯");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("T2：拿茶叶");
            TimeUnit.SECONDS.sleep(1);
            return "峨眉雪尖儿";
        }
    }

   public static  void main(String[] args){
    // 创建 FutureTask_2 的任务
    FutureTask<String> futureTask_2 = new FutureTask<>(new FutureTask_2());
    // 创建 FutureTask_1 的任务，并将 FutureTask_2 任务的引用传入
    FutureTask<String> futureTask_1 = new FutureTask<>(new FutureTaskDemo1(futureTask_2));
    // 创建线程 T1，来执行任务 FutureTask_1
    Thread t1 = new Thread(futureTask_1);
    t1.start();
// 创建线程 T2，来执行任务 FutureTask_2
    Thread t2 = new Thread(futureTask_2);
    t2.start();
    try {
        // 获取任务 FutureTask_1 的最后一步的结果
        System.out.println(futureTask_1.get());
    } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
    }
   }


}
