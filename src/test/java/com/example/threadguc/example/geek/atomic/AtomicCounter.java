package com.example.threadguc.example.geek.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * AtomicInteger  尽管 AtomicInteger 不能解决 ABA 问题，但它在很多情况下仍然可以有效使用，特别是当不涉及到复杂的并发场景时。具体来说：
 * 自增/自减操作：AtomicInteger 适用于简单的自增、自减操作，且没有发生 ABA 问题的风险。
 * 计数器等场景：对于一些简单的计数器（如常见的并发计数器），AtomicInteger 已经足够，因为这类操作通常不需要关心值的变化历史。
 * @date 2024/6/28 21:51
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AtomicCounter {
    private final AtomicInteger counter = new AtomicInteger(0);

    public int getValue() {
        return counter.get();
    }
    @Test
    public void increment() {
        while(true) {
            int existingValue = getValue();
            int newValue = existingValue + 1;
            if(counter.compareAndSet(existingValue, newValue)) {
                log.info("increment: {}", getValue());
                return;
            }
        }
    }



}
