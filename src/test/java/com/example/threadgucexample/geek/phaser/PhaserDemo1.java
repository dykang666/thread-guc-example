package com.example.threadgucexample.geek.phaser;

import java.util.concurrent.Phaser;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * Phaser主要用来解决什么问题?
 * Phaser又称“阶段器” 阶段协同器
 * 用来解决控制多个线程分阶段共同完成任务的情景问题
 * 它特别适合于那些需要在多个阶段进行协调的复杂并发场景。
 * 相比CountDownLatch和CyclicBarrier就会变得更加灵活
 *
 *
 * 它可以实现CyclicBarrier和CountDownLatch类似的功能
 * Phaser与CyclicBarrier和CountDownLatch的区别是什么?
 * 如果用CountDownLatch来实现Phaser的功能应该怎么实现?
 * Phaser运行机制是什么样的?
 * 给一个Phaser使用的示例?
 * ------
 * Phaser类的目的是允许在并发编程中同步多个线程之间的执行，它具有如下优点，如下：
 *
 * 更好的可扩展性：Phaser类相对于其他同步工具类（如CyclicBarrier和CountDownLatch）具有更好的可扩展性，因为它支持更多的参与者（即线程）同时进行同步。
 * 自动注销和清理：当所有参与者都完成执行后，Phaser会自动注销并释放相关资源，这有助于避免内存泄漏和资源浪费。
 * 灵活的执行模式：Phaser类提供了多种执行模式，如并行、串行和混合模式，这使得在处理并发任务时更加灵活。
 * 著作权归@pdai所有
 * 原文链接：https://pdai.tech/md/java/thread/java-thread-x-juc-tool-phaser.html
 * @date 2024/6/29 17:19
 */
public class PhaserDemo1 {
    //可以看出并不会造成主线程的阻塞，任务也是分阶段去完成的，

    public static void main(String[] args) {
        //等待直到屏障推进到给定的阶段，如果当前阶段大于或等于给定的阶段，那么此方法将立即返回。
        Phaser phaser = new Phaser(10);
        for (int i=0; i<10; i++){
            new Thread(() -> {
                try {
                    long millis = System.currentTimeMillis();
                    System.out.println(millis + "--1-->当前处于"+phaser.getPhase()+"阶段");
                    Thread.sleep(1000);
                    // wait
                    phaser.arriveAndAwaitAdvance();
                    System.out.println(millis + "---2--->当前处于"+phaser.getPhase()+"阶段");
                    Thread.sleep(1000);
                    // wait
                    phaser.arriveAndAwaitAdvance();
                    System.out.println(millis + "---3--->当前处于"+phaser.getPhase()+"阶段");
                    Thread.sleep(1000);
                    // wait
                    phaser.arriveAndAwaitAdvance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("主线程");

    }
}
