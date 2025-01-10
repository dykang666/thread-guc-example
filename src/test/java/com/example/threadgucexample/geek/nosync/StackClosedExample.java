package com.example.threadgucexample.geek.nosync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/7/3 13:12
 */
public class StackClosedExample {
    //多个线程访问同一个方法的局部变量时，不会出现线程安全问题，因为局部变量存储在虚拟机栈中，属于线程私有的。
    public void add100() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            cnt++;
        }
        System.out.println(cnt);
    }
    public static void main(String[] args) {
        StackClosedExample example = new StackClosedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> example.add100());
        executorService.execute(() -> example.add100());
        executorService.shutdown();
    }

}
