package com.example.threadgucexample.geek.futer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.*;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * Future代表的是异步执行的结果，意思是当异步执行结束之后，返回的结果将会保存在Future中。
 * @date 2024/6/28 16:57
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FutureDemo {


    @Test
    public  void t1() throws ExecutionException, InterruptedException {
        // 执行任务 实现Runnable
        FutureTaskJobRunnable taskRun = new FutureTaskJobRunnable();
        // 执行任务 实现Callable
        FutureTaskJobCallable taskCall = new FutureTaskJobCallable();
        // 线程运行，返回线程执行的结果
        FutureTask<String> futureTaskCall = new FutureTask<String>(taskCall);
        String val = "ok";
        // 线程运行成功后把,返回你传入的val值
        FutureTask<String> futureTaskRun = new FutureTask<String>(taskRun, val);

        //声明线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //Future
        Future<String> future =  executor.submit(taskCall);
        log.info(future.get());
        //FutureTask
        executor.submit(futureTaskCall);
        System.out.println(futureTaskCall.get());
        //FutureTask自定义线程执行
        new Thread(futureTaskRun).start();
        System.out.println(futureTaskRun.get());


    }
    public  class FutureTaskJobRunnable implements Runnable {

        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("FutureTaskJobRunnable已经执行了哦");
        }

    }





    public  class FutureTaskJobCallable implements Callable<String> {
        public String call() throws Exception {
            System.out.println("FutureTaskJobCallable已经执行了哦");
            Thread.sleep(1000);
            return "返回结果";
        }

    }
}
