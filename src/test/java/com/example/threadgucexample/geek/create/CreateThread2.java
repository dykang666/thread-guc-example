package com.example.threadgucexample.geek.create;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 12:14
 */
public class CreateThread2 {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("你好吗！ 小菜");
            }
        } ;

        t.start();
    }
}
