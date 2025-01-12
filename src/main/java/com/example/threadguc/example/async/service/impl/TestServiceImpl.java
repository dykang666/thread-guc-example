package com.example.threadguc.example.async.service.impl;


import com.example.threadguc.example.async.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/12/23 15:39
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        System.out.println("ThreadName:" + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试Spring 异步调用！");
    }

    @Override
    public void test2() {
        test();//自调用test()方法

    }


}
