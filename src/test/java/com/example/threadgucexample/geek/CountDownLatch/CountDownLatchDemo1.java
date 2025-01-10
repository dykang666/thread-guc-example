package com.example.threadgucexample.geek.CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 *
 *  * 使用CountDownLatch 代替wait notify 好处是通讯方式简单，不涉及锁定  Count 值为0时当前线程继续执行，
 *
 * @date 2024/6/29 16:11
 */
public class CountDownLatchDemo1 {

    volatile List list = new ArrayList();

    public void add(int i){
        list.add(i);
    }

    public int getSize(){
        return list.size();
    }

    public static void main(String[] args) {
        CountDownLatchDemo1 t = new CountDownLatchDemo1();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(()->{
            System.out.println("t2 start");
            if(t.getSize() != 5){
                try {
                    countDownLatch.await();//等待
                    System.out.println("t2 end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();
        new Thread(()->{
            System.out.println("t1 start");
            for (int i = 0;i<9;i++){
                t.add(i);
                System.out.println("add"+ i);
                if(t.getSize() == 5){
                    System.out.println("countdown is open");
                    countDownLatch.countDown();
                }
            }
            System.out.println("t1 end");
        },"t1").start();
    }



}


