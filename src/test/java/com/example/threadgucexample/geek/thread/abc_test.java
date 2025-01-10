package com.example.threadgucexample.geek.thread;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/7/3 20:47
 */
public class abc_test {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread thread =new Thread(new joinDemo());
        thread.start();
        for(int i=0;i<20;i++){
            System.out.println("主线程第"+i+"此执行！");
            if(i>=2){
                try{
                    //t1线程合并到主线程中，主线程停止执行过程，转而执行t1线程，直到t1执行完毕后继续；
                    thread.join();
                    //thread.sin
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }
    static  class joinDemo implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            for(int i=0;i<10;i++){
                System.out.println("线程1第"+i+"次执行");
            }

        }
    }
    }
