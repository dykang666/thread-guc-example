package com.example.threadguc.example.geek.asynCode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * 尤其适用于 I/O 密集型任务（如网络请求、数据库查询等）以及长时间运行的计算任务。
 * @date 2025/1/1 18:53
 */
public class CompletableFutureWithExceptionHandling {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 异步任务
        CompletableFuture<Integer> futureTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务开始执行...");
            if (true) { // 模拟异常
                throw new RuntimeException("任务失败!");
            }
            return 10;
        });

        // 异常处理
        futureTask.exceptionally(ex -> {
            System.out.println("捕获异常: " + ex.getMessage());
            return -1; // 返回默认值
        });

        // 等待任务完成并获取结果
        Integer result = futureTask.get();
        System.out.println("任务的最终结果: " + result);
    }

}
