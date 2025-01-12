package com.example.threadguc.example.async.controller;


import com.example.threadguc.example.async.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/12/23 15:41
 */

@RestController
@RequestMapping(value = "/test")
@EnableAsync
public class TestContoller {
    @Autowired
    private TestService testService;

    @GetMapping(value = "/testAsync")
    public void print() {
        System.out.println("ThreadName:" + Thread.currentThread().getName());
        System.out.println("当前线程开始执行测试函数......");
        //调用有@Async注解的test()方法
        testService.test();
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + " ");
            if (i % 10 == 0) {
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("当前线程测试函数执行完毕......");
    }

    @GetMapping(value = "/testAsync2")
    public void print2() {
        System.out.println("ThreadName:" + Thread.currentThread().getName());
        System.out.println("当前线程开始执行测试函数......");
        testService.test2();//改成调用没有@Async注解修饰的test2()方法
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + " ");
            if (i % 10 == 0) {
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("当前线程测试函数执行完毕......");
    }
}
