package com.example.threadgucexample.geek.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * 1、Java的concurrent包里面的CountDownLatch其实可以把它看作一个计数器，
 * 只不过这个计数器的操作是原子操作，同时只能有一个线程去操作这个计数器，也就是同时只能有一个线程去减这个计数器里面的值。
 * 2、可以向CountDownLatch对象设置一个初始的数字作为计数值，任何调用这个对象上的await()方法都会阻塞，
 * 直到这个计数器的计数值被其他的线程减为0为止。
 * example
 * 比如：客户端一次请求5个统计数据，服务器需要全部统计完成后，才返回客户端，可以使用CountDownLatch 。
 * @date 2024/6/28 15:14
 * 使用举例
 * 首先是创建实例 CountDownLatch countDown = new CountDownLatch(2)
 * 需要同步的线程执行完之后，计数-1； countDown.countDown()
 * 需要等待其他线程执行完毕之后，再运行的线程，调用 countDown.await()实现阻塞同步
 * 使用场景
 * 前面给了一个demo演示如何用，那这个东西在实际的业务场景中是否会用到呢？
 *
 * 因为确实在一个业务场景中使用到了，不然也就不会单独捞出这一节...
 * 电商的详情页，由众多的数据拼装组成，如可以分成一下几个模块
 * 交易的收发货地址，销量
 * 商品的基本信息（标题，图文详情之类的）
 * 推荐的商品列表
 * 评价的内容
 *计时器的功能  CountDownLatch
 */
public class CountDownLatchDemo {



    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread(()->{
            try {
                System.out.println("子线程" + Thread.currentThread().getName()
                        + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName()
                        + "执行完毕");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
                try {
                    System.out.println("子线程" + Thread.currentThread().getName()
                            + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName()
                            + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        },"t2").start();
        try {
            System.out.println("等待2个子线程执行完毕...");
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
