package com.example.threadgucexample.geek.atomic;

import lombok.Data;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 21:50
 */
@Data
public class LockCounter {
    private volatile int counter;
    public synchronized void increment() {
        counter++;
    }
}
