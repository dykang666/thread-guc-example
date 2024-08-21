package com.example.threadgucexample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * （2）实际工作中遇到过ABA问题吗？
 * 笔者还真遇到过，以前做棋牌游戏的时候，ABCD四个玩家，A玩家出了一张牌，然后他这个请求迟迟没到服务器，也就是超时了，服务器就帮他自动出了一张牌。
 * 然后，转了一圈，又轮到A玩家出牌了，说巧不巧，正好这时之前那个请求到了服务器，服务器检测到现在正好是A出牌，而且请求的也是出牌，就把这张牌打出去了。
 * 然后呢，A玩家的牌就不对了。
 * 小明在提款机，提取了50元，因为提款机问题，有两个线程，同时把余额从100变为50：
 *
 * 线程1（提款机）：获取当前值100，期望更新为50；
 * 线程2（提款机）：获取当前值100，期望更新为50；
 * 线程1成功执行，线程2某种原因block了；
 * 这时，某人给小明汇款50；
 * 线程3（默认）：获取当前值50，期望更新为100，这时候线程3成功执行，余额变为100；
 * 线程2从Block中恢复，获取到的也是100，compare之后，继续更新余额为50。
 * @date 2024/6/28 11:14
 */
@Slf4j
@RestController
public class ABAController {
    private static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    private static AtomicStampedReference<Integer> stampedReference=new AtomicStampedReference<>(100,1);
    // ABA问题产生
    @GetMapping("/testABA")
    public void testABA() {
        log.info("===以下是ABA问题的产生===");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 暂停1秒钟t1线程
            log.info( atomicReference.compareAndSet(101,300)+ "\t" + atomicReference.get());
        },"t1").start();
        new Thread(()->{
            atomicReference.compareAndSet(101, 100);
        },"t2").start();
    }

    // ABA问题解决
    @GetMapping("/testABAResolve")
    public void testABAResolve() {
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
