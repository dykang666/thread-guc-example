package com.example.threadguc.example.geek.atomic;

import lombok.Data;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 21:50
 */
@Data
public class Counter {
    int counter;

    public void increment() {
        counter++;
    }
}
