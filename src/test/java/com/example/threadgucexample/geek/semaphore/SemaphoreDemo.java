package com.example.threadgucexample.geek.semaphore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Semaphore;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: 计数信号量
 * @date 2024/6/28 16:37
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SemaphoreDemo implements Runnable {
    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            semaphore.acquire();//抢占 cas 是获取锁方法
            System.out.println(Thread.currentThread().getName()+":doning!");
            Thread.sleep(2000); //当前线程进入睡眠
            System.out.println(Thread.currentThread().getName()+":done!");
            semaphore.release();//释放
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public static void main(String[] args) throws InterruptedException {
        final SemaphoreDemo semaphore1=new SemaphoreDemo();
        Thread.sleep(2000); //当前线程进入睡眠
       // System.out.println(Thread.currentThread().getName()+"睡眠会儿");
        Thread[] thread = new Thread[20];
        for (int i=0 ; i<20 ; i++){
            thread[i] = new Thread(semaphore1);
        }
        for (int i=0 ; i<10 ; i++){
            thread[i].start();
        }
    }
}
