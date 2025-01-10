package com.example.threadgucexample.geek.create;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 12:15
 */
public class CreateThread3 implements  Runnable{
    public static void main(String[] args) {
        Thread t = new Thread(new CreateThread3());
        t.start();
    }
    @Override
    public void run() {
        System.out.println("你好吗 小菜！");
    }
}
