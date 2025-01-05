package com.example.threadgucexample.geek.collections;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 *  CopyOnWriteArrayList 是一个线程安全的列表，它使用了 Copy-On-Write（复制-写入）策略，
 *      即在写入操作时，会先复制一份当前列表，然后在新的副本上进行修改，最后将新的副本替换原来的列表。
 *      这样可以保证在遍历列表时，不会受到写入操作的影响。
 *      适合读多写少的场景，因为写入操作会创建新的副本，对性能有影响。  读写分离
 *      底层实现是 数组 + ReentrantLock(原子性和一致性)+volatile
 * @date 2024/12/31 17:05
 */
public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("苹果");
        list.add("香蕉");
        list.add("橙子");
        // 开启一个线程修改列表
        new Thread(() -> {
            list.add("葡萄");
            list.remove("香蕉");
        }).start();

        // 同时遍历列表
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}
