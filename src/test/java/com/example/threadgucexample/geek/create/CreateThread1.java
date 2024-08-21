package com.example.threadgucexample.geek.create;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 12:14
 */
public class CreateThread1 {
    public static void main(String[] args) {
        Thread t = new Thread("你好");
        System.out.println(t.getName());
        t.start();
    }
}
