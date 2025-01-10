package com.example.threadgucexample.geek.threadLocal;

import java.util.Random;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * ThreadLocal是通过线程隔离的方式防止任务在共享资源上产生冲突,
 * 线程本地存储是一种自动化机制，可以为使用相同变量的每个不同线程都创建不同的存储
 * @date 2024/6/30 9:43
 */
public class ThreadLocalDemo2 implements Runnable {

    class Student {
        private int age;
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }
    ThreadLocal<Student> StudentThreadLocal = new ThreadLocal<Student>();

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
        Random random = new Random();
        int age = random.nextInt(100);
        System.out.println(currentThreadName + " is set age: "  + age);
        Student Student = getStudentt(); //通过这个方法，为每个线程都独立的new一个Studentt对象，每个线程的的Studentt对象都可以设置不同的值
        Student.setAge(age);
        System.out.println(currentThreadName + " is first get age: " + Student.getAge());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // StudentThreadLocal.remove();
        System.out.println( currentThreadName + " is second get age: " + Student.getAge());
    }

    private Student getStudentt() {
        Student Student = StudentThreadLocal.get(); // 使用
        if (null == Student) {
            Student = new Student();
            StudentThreadLocal.set(Student);
        }
        return Student;
    }

    public static void main(String[] args) {
        ThreadLocalDemo2 t = new ThreadLocalDemo2();
        Thread t1 = new Thread(t,"Thread A");
        Thread t2 = new Thread(t,"Thread B");
        t1.start();
        t2.start();
    }

}
