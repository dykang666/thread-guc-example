package com.example.threadgucexample.geek.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: https://juejin.cn/post/6844904195162636295
 * @date 2024/7/4 12:01
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) {
        CompletableFuture future =   CompletableFuture.supplyAsync(() -> {
            System.out.println("电饭煲开始做饭");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "白米饭";
        }).thenAccept(result -> {
            System.out.println("开始吃米饭");
        });
        System.out.println("我先去搞点牛奶和鸡蛋");
        future.join();

    }
}
