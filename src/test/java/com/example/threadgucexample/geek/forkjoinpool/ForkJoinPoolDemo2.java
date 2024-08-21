package com.example.threadgucexample.geek.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author kangdongyang
 * @version 1.0  阶段器
 * @description: 分叉、加入
 * @date 2024/6/29 15:57
 */
public class ForkJoinPoolDemo2  extends RecursiveTask<Integer> {

    final int n;
    ForkJoinPoolDemo2(int n) {
        this.n = n;
    }
    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        ForkJoinPoolDemo2 f1 = new ForkJoinPoolDemo2(n - 1);
        f1.fork();
        ForkJoinPoolDemo2 f2 = new ForkJoinPoolDemo2(n - 2);
        return f2.compute() + f1.join();
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4); // 最大并发数4
        ForkJoinPoolDemo2 fibonacci = new ForkJoinPoolDemo2(20);
        long startTime = System.currentTimeMillis();
        Integer result = forkJoinPool.invoke(fibonacci);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }
}
