package com.example.threadgucexample.geek.collections.concurrentmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * 分段锁  线程安全的map
 *  底层cas  +   volatile  +红黑树+ 细粒度的锁机制（锁分离）+分段锁+读写分离
 * @date 2024/12/31 17:03
 */
public class ConcurrentHashMapExample {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        // 使用forEach遍历
        map.forEach((key, value) -> System.out.println(key + " = " + value));
    }
}
