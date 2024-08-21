package com.example.threadgucexample.geek.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: ABA  当涉及到引用的时候就会出问题
 * 场景是用链表来实现一个栈，初始化向栈中压入B、A两个元素，栈顶head指向A元素。
 *
 * 在某个时刻，线程1试图将栈顶换成B，但它获取栈顶的oldValue（为A）后，被线程2中断了。线程2依次将A、B弹出，然后压入C、D、A。
 * 然后换线程1继续运行，线程1执行compareAndSet发现head指向的元素确实与oldValue一致，都是A，所以就将head指向B了。
 * 但是线程2在弹出B的时候，将B的next置为null了，因此在线程1将head指向B后，栈中只剩了一个孤零零的元素B。但按预期来说，栈中应该放的是B → A → D → C
 * 原文链接：https://blog.csdn.net/qq_27631797/article/details/106721344
 * @date 2024/6/28 10:39
 */
@Slf4j
public class ABAdemo {
    //AtomicReference类提供了一个可以原子读写的对象引用变量
    private static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    //Java中使用 AtomicStampedReference 来解决 CAS 中的ABA问题
    //它不再像 compareAndSet 方法 中只比较内存中的值与当前值是否相等，
    //而且先比较引用是否相等，然后比较值是否相等，这样就避免了ABA问题。
    private static AtomicStampedReference<Integer> stampedReference=new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        log.info("===以下是ABA问题的产生===");
    new Thread(()->{
        atomicReference.compareAndSet(100,101);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info( atomicReference.compareAndSet(100,300)+ "\t" + atomicReference.get());
    },"t1").start();
    new Thread(()->{
       atomicReference.compareAndSet(101, 100);
        },"t2").start();

        log.info("===以下是ABA问题的解决===");
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第1次版本号" + stamp + "\t值是" + stampedReference.getReference());
            //暂停1秒钟t3线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 第2次版本号" + stampedReference.getStamp() + "\t值是" + stampedReference.getReference());
            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t 第3次版本号" + stampedReference.getStamp() + "\t值是" + stampedReference.getReference());
        }, "t3").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第1次版本号" + stamp + "\t值是" + stampedReference.getReference());
            //保证线程3完成1次ABA
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("====================="+stampedReference.getStamp());
            boolean result = stampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t 修改成功否" + result + "\t最新版本号" + stampedReference.getStamp());
            System.out.println("最新的值\t" + stampedReference.getReference());
        }, "t4").start();
    }




}
