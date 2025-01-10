package com.example.threadgucexample.geek.threadLocal;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * 多线程是Java实现多任务的基础,Thread对象代表一个线程
 * 提到ThreadLocal被提到应用最多的是session管理和数据库链接管理，这里以数据访问为例帮助你理解ThreadLocal：
 * 主要是用到了Thread对象中的一个ThreadLocalMap类型的变量threadLocals,
 *  应用场景：
 *  1、
 *  2、
 *  3、
 *  4、
 * @date 2024/6/29 19:17
 */
public class ThreadLocalDemo1 {
    public static void main(String[] args) throws Exception {
        log("start main...");
        new Thread(() -> {
            log("run task...");
        }).start();
        new Thread(() -> {
            log("print...");
        }).start();
        log("end main.");
    }
    static void log(String s) {
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }

}
