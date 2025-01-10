package com.example.threadgucexample.geek.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 12:07
 */
public class AtomicMarkableReferenceDemo {

    private static AtomicMarkableReference<Integer> atomicMarkableReference = new AtomicMarkableReference<Integer>(100,false);

    public static void main(String[] args) {
        // 第一个线程
        new Thread(() -> {
            System.out.println("t1版本号是否被更改:" + atomicMarkableReference.isMarked());
            //睡眠1秒，是为了让t2线程也拿到同样的初始版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicMarkableReference.compareAndSet(100, 101,atomicMarkableReference.isMarked(),true);
            atomicMarkableReference.compareAndSet(101, 100,atomicMarkableReference.isMarked(),true);
        },"t1").start();

        // 第二个线程
        new Thread(() -> {
            boolean isMarked = atomicMarkableReference.isMarked();
            System.out.println("t2版本号是否被更改:" + isMarked);
            //睡眠3秒，是为了让t1线程完成ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("是否更改过:" + atomicMarkableReference.isMarked());
            System.out.println(atomicMarkableReference.compareAndSet(100, 2019,isMarked,true) + "\t当前值:" + atomicMarkableReference.getReference());
        },"t2").start();

    }



}
