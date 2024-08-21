package com.example.threadgucexample.geek.phaser.phaser1;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author kangdongyang
 * @version 1.0
 * @description: 又称“阶段器”
 * @date 2024/6/29 17:49
 */
@Slf4j
public class PhaserDemo2 {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }
        public  void milliSleep(int milli) {
            try {
                TimeUnit.MILLISECONDS.sleep(milli);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场！\n", name);
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完!\n", name);
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开！\n", name);
        }
    }

    static class MarriagePhaser extends Phaser {


        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            //onAdvance 提前
            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了！");
                    return false;
                case 1:
                    System.out.println("所有人吃完了！");
                    return false;
                case 2:
                    System.out.println("所有人离开了！");
                    System.out.println("婚礼结束！");
                    return true;
                default:
                    return true;
            }
        }
    }


        public static void main(String[] args) {
            phaser.bulkRegister(5); //添加指定数量的多个注册者  一定等于线程数
            for (int i = 0; i < 5; i++) {
                final int nameIndex = i;
                new Thread(() -> {
                    Person p = new Person("person " + nameIndex);
                    p.arrive();
                    phaser.arriveAndAwaitAdvance();//分阶段方法 类似CyclicBarrier.await
                    p.eat();
                    phaser.arriveAndAwaitAdvance();//分阶段方法
                    p.leave();
                    phaser.arriveAndAwaitAdvance();//分阶段方法
                }).start();
            }
    }
}


