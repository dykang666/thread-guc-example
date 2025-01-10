package com.example.threadguc.example.async.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/12/23 15:38
 */
public interface TestService {
    @Async
    void test();
    void test2();

}
