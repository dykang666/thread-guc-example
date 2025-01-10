package com.example.threadgucexample.geek.looksupport;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 22:25
 */
public class LookSupport1 extends Thread{
    public void run() {
        synchronized (this) {
            System.out.println("before notify");
            notify();
            System.out.println("after notify");
        }
    }


    public static void main(String[] args) {
        LookSupport1 myThread = new LookSupport1();
        synchronized (myThread) {
            try {
                myThread.start();
                // 主线程睡眠3s
               Thread.sleep(1000);
                System.out.println("before wait");
                // 阻塞主线程 ----释放锁
               myThread.wait();
                System.out.println("after wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        synchronized (myThread) {
//            //主线程执行完 执行子线程
//                myThread.start();
//                // 主线程睡眠3s
//                // Thread.sleep(1000);
//                System.out.println("before wait");
//                // 阻塞主线程
//                //myThread.wait();
//                System.out.println("after wait");
//
//        }
    }



}
