package com.example.threadgucexample.geek.join;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 18:12
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Data
public class JoinDemo1 extends Thread {
    public int processingCount = 0;
    @Override
    public void run() {
        log.info("Thread " + Thread.currentThread().getName() + " started");
        while (processingCount > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.info("Thread " + Thread.currentThread().getName() + " interrupted");
            }
            processingCount--;
        }
        log.info("Thread " + Thread.currentThread().getName() + " exiting");
    }
//@Test方法所在类中,不能存在有参数构造函数,无参构造可以存在
    @Test
    public  void joinTest()
            throws InterruptedException {
       JoinDemo1 joinDemo1 = new JoinDemo1();
       joinDemo1.setProcessingCount(1);
       Thread t2 = new Thread(joinDemo1);
        t2.start();
        log.info("Invoking join");
        t2.join();
        log.info("Returned from join");
        log.info("t2 status {}",t2.isAlive());
    }
    @Test
    public void testJoinTimeout()
            throws InterruptedException {
        JoinDemo1 joinDemo1 = new JoinDemo1();
        joinDemo1.setProcessingCount(10);
        Thread t3 =  new Thread(joinDemo1);
        t3.start();
        t3.join(1000);
        log.info("t3 status {}", t3.isAlive());
    }
    @Test
    public void testHappenBefore() throws InterruptedException {
        JoinDemo1 joinDemo1 = new JoinDemo1();
        joinDemo1.setProcessingCount(10);
        Thread t4 =  new Thread(joinDemo1);
        t4.start();
        // not guaranteed to stop even if t4 finishes.
        do {
            log.info("inside the loop");
            Thread.sleep(1000);
        } while ( processingCount > 0);
    }

}

