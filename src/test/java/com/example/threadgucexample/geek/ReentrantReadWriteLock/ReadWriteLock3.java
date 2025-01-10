package com.example.threadgucexample.geek.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/29 11:47
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ReadWriteLock3  {



    @Test
    public  void t1() throws InterruptedException {
        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock r = rw.readLock();
        ReentrantReadWriteLock.WriteLock w = rw.writeLock();
        Thread thread0 = new Thread(() -> {
            r.lock();
            try {
                Thread.sleep(1000);
                System.out.println("Thread 1 running " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r.unlock();
            }
        },"t1");

//        Thread thread1 = new Thread(() -> {
//            r.lock();
//            try {
//                Thread.sleep(1000);
//                System.out.println("Thread 2 running " + new Date());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                r.unlock();
//            }
//        },"t2");

        Thread thread1 = new Thread(() -> {
            w.lock();
            try {
                Thread.sleep(1000);
                System.out.println("Thread 2 running " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                w.unlock();
            }
        },"t2");

        //读写两个线程间隔1秒，互斥执行
        thread0.start();
        thread1.start();
        //但是，join()只会释放Thread的锁，不会释放线程对象的锁（可能会造成死锁）。
        thread0.join();
        thread1.join();
    }









}
