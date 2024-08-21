package com.example.threadgucexample.geek.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 12:05
 */
public class CasDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2019));
        System.out.println(atomicInteger.compareAndSet(5,2020));
        System.out.println(atomicInteger.get());

    }
}
