package com.example.threadgucexample.geek.aqs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/29 11:21
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReentrantReadWriteLockDemo {
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock readLock1 = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public void read(String read) {
        readLock.lock();
        try {
            // 执行读
            log.info("执行读");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("read:{}",read);
        } finally {
            readLock.unlock();
        }
    }
    public void readNew(String readNew) {
        readLock.lock();
        try {
            // 执行读
            log.info("执行读");
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("read:{}",readNew);
        } finally {
            readLock.unlock();
        }
    }
    public  void write() {
        writeLock.lock();
        try {
            //执行写
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("执行写.....");
        } finally {
            writeLock.unlock();
        }
    }

    @Test
    public void T1() {
        new Thread(() -> {
            read("read");
        }, "t1").start();
        new Thread(() -> {
            read("read");
        }, "t2").start();
       // write();
       // read("read");
       // read("read");
       // readNew("readNew");


    }


}
