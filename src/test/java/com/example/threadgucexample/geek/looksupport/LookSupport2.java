package com.example.threadgucexample.geek.looksupport;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: 使用wait/notify实现同步时，必须先调用wait，后调用notify，
 * 如果先调用notify，再调用wait，将起不了作用。具体代码如下
 * LookSupport1
 * @date 2024/6/28 22:32
 */
public class LookSupport2  extends Thread {
    public void run() {
        synchronized (this) {
            System.out.println("before notify");
            notify();
            System.out.println("after notify");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LookSupport2 myThread = new LookSupport2();
        myThread.start();
        // 主线程睡眠3s
        Thread.sleep(3000);
        synchronized (myThread) {
            try {
                System.out.println("before wait");
                // 阻塞主线程
                myThread.wait();
                System.out.println("after wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}




