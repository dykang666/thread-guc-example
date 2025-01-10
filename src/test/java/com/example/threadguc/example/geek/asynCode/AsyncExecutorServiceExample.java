package com.example.threadguc.example.geek.asynCode;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2025/1/1 18:48
 */
public class AsyncExecutorServiceExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // 提交异步任务
        Future<Integer> futureTask1 = executorService.submit(() -> {
            System.out.println("任务 1 开始执行...");
            Thread.sleep(2000); // 模拟耗时操作
            System.out.println("任务 1 执行完成...");
            return 10;
        });

        Future<Integer> futureTask2 = executorService.submit(() -> {
            System.out.println("任务 2 开始执行...");
            Thread.sleep(1000); // 模拟耗时操作
            System.out.println("任务 2 执行完成...");
            return 20;
        });

        // 等待任务完成并获取结果
        Integer result1 = futureTask1.get();  // 阻塞等待任务 1 完成
        Integer result2 = futureTask2.get();  // 阻塞等待任务 2 完成

        // 打印结果
        System.out.println("任务 1 的结果: " + result1);
        System.out.println("任务 2 的结果: " + result2);

        // 关闭线程池
        executorService.shutdown();
    }
}
