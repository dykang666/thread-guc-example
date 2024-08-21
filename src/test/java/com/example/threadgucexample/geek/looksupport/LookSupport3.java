package com.example.threadgucexample.geek.looksupport;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: 是一个线程阻塞工具类
 * @date 2024/6/28 22:39
 */
@Slf4j
public class LookSupport3 extends Thread {

    private Object object;

    public LookSupport3(Object object) {
        this.object = object;
    }

    public void run() {
        //Thread.sleep()出现在哪里，那么哪里就得sleep！
        System.out.println("before unpark");
        try {
            log.info("Thread  name:" + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //log.info("Thread  name:" + Thread.currentThread().getName());
        // 获取blocker
        System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));
        // 释放许可
        LockSupport.unpark((Thread) object);
        // 休眠500ms，保证先执行park中的setBlocker(t, null);
        try {
            log.info("Thread  name:" + Thread.currentThread().getName());
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再次获取blocker
        System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));
        System.out.println("after unpark");
    }



        public static void main(String[] args) {
            LookSupport3 myThread = new LookSupport3(Thread.currentThread());
            //log.info("main start myThread name:" + Thread.currentThread().getName());
            myThread.start();
            System.out.println("before park");
            // 获取许可
            LockSupport.park("ParkAndUnparkDemo");
            LockSupport.park();
            System.out.println("after park");
        }

}







