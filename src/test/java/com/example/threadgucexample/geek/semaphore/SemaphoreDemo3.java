package com.example.threadgucexample.geek.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/29 17:16
 */
public class SemaphoreDemo3 extends Thread {
    private Semaphore semaphore;

    public SemaphoreDemo3(String name, Semaphore semaphore) {
        super(name);
        this.semaphore = semaphore;
    }
    public void run() {
        int count = 3;
        System.out.println(Thread.currentThread().getName() + " trying to acquire");
        try {
            semaphore.acquire(count);
            System.out.println(Thread.currentThread().getName() + " acquire successfully");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(count);
            System.out.println(Thread.currentThread().getName() + " release successfully");
        }
    }

    public static void main(String[] args) {
         final  int SEM_SIZE = 10;
        Semaphore semaphore = new Semaphore(SEM_SIZE);
        SemaphoreDemo3 t1 = new SemaphoreDemo3("t1", semaphore);
        SemaphoreDemo3 t2 = new SemaphoreDemo3("t2", semaphore);
        t1.start();
        t2.start();
        int permits = 5;
        System.out.println(Thread.currentThread().getName() + " trying to acquire");
        try {
            semaphore.acquire(permits);
            System.out.println(Thread.currentThread().getName() + " acquire successfully");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " release successfully");
        }


    }



}
