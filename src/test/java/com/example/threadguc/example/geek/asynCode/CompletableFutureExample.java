package com.example.threadguc.example.geek.asynCode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * 是完全非阻塞的
 * @date 2025/1/1 18:50
 */
public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步任务 1
        CompletableFuture<Integer> futureTask1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务 1 开始执行...");
            try {
                Thread.sleep(2000); // 模拟耗时操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务 1 执行完成...");
            return 10;
        });

        // 异步任务 2
        CompletableFuture<Integer> futureTask2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务 2 开始执行...");
            try {
                Thread.sleep(1000); // 模拟耗时操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务 2 执行完成...");
            return 20;
        });

        // 合并两个任务的结果
        CompletableFuture<Integer> result = futureTask1.thenCombine(futureTask2, (result1, result2) -> {
            System.out.println("任务 1 和 任务 2 都执行完成，合并结果...");
            return result1 + result2;
        });

        // 等待任务完成并获取结果
        Integer finalResult = result.get();
        System.out.println("最终结果: " + finalResult);
    }
}
