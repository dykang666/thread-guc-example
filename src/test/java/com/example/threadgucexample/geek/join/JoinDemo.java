package com.example.threadgucexample.geek.join;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 18:10
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JoinDemo {

    public volatile static int i = 0;

    public static class AddThread extends Thread {
        @Override
        public void run() {

            for (i = 0; i < 10000000; i++){

            } ;
            System.out.println("i="+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        //等待的是主线程
        at.join();
        System.out.println(i);
    }
}
