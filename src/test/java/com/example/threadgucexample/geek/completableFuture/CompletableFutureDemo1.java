package com.example.threadgucexample.geek.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: https://juejin.cn/post/7055646857241821215
 * CompletableFuture 提供了高度灵活的异步编程模型，能够简化并发编程，提高程序的响应性和性能。
 * 1、thenApply提交的任务类型需遵从Function签名，也就是有入参和返回值，其中入参为前置任务的结果
 * 2、实际上thenAccept的效果，和thenApply 的效果等同，但是thenAccept提交的任务类型需遵从Consumer签名，也就是有入参但是没有返回值，其中入参为前置任务的结果。
 * 3、whenComplete主要用于注入任务完成时的回调通知逻辑
 * 4、
 *
 * @date 2024/7/1 12:26
 */
public class CompletableFutureDemo1 {

    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Hello";
                }, executor)
                .thenApply(s -> s + " World")
                .thenAccept(System.out::println)
                .exceptionally(ex -> {
                    System.err.println("Exception occurred: " + ex.getMessage());
                    return null;

                });


    }
}
