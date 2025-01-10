package com.example.threadgucexample.geek.looksupport;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * 说明: 可以看到，在先调用unpark，再调用park时，仍能够正确实现同步，不会造成由wait/notify调用顺序不当所引起的阻塞。
 * 因此park/unpark相比wait/notify更加的灵活。
 * ------
 * 著作权归@pdai所有
 * 原文链接：https://pdai.tech/md/java/thread/java-thread-x-lock-LockSupport.html
 * @date 2024/6/28 22:56
 */
@Slf4j
public class LookSupport4  extends Thread{

    private Object object;

    public LookSupport4(Object object) {
        this.object = object;
    }

    public void run() {
        System.out.println("before unpark");

        log.info( "(Thread) object"+(Thread) object);
        // 释放许可
        LockSupport.unpark((Thread) object);

        System.out.println("after unpark");
    }
    public static void main(String[] args) {
        LookSupport4 myThread = new LookSupport4(Thread.currentThread());
        myThread.start();
        try {
            // 主线程睡眠3s
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("before park");
        // 获取许可
        LockSupport.park("ParkAndUnparkDemo");
        System.out.println("after park");
    }





}
