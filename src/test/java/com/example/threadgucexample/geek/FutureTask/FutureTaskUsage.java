package com.example.threadgucexample.geek.FutureTask;

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
 * @date 2024/6/28 17:22
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class FutureTaskUsage {
    @Test
    public void convertRunnableToCallable() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("inside callable future task ...");
                return 0;
            }
        });

        Thread thread= new Thread(futureTask);
        thread.start();
        log.info(futureTask.get().toString());
    }

    @Test
    public void workWithExecutorService() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("inside futureTask");
                return 1;
            }
        });
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(futureTask);
        executor.shutdown();
        log.info(futureTask.get().toString());
    }
}
