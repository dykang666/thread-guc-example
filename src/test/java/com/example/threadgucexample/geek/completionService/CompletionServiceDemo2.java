package com.example.threadgucexample.geek.completionService;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/7/4 22:06
 */
@Slf4j
public class CompletionServiceDemo2 {
    @Test
    public  void T1() throws ExecutionException, InterruptedException {
        LocalDateTime start = LocalDateTime.now();
        CompletionService completionService = new ExecutorCompletionService(Executors.newFixedThreadPool(4));
          completionService.submit(new CsCallable("xxx", 5000));
          completionService.submit(new CsCallable("www", 2000));
          completionService.submit(new CsCallable("zzz", 14000));
          completionService.submit(new CsCallable("yyy", 9000));
        for (int i = 0; i < 4; i++) {
        System.out.println(completionService.take().get());
           }
           System.out.println("----- main over -----");
        LocalDateTime end = LocalDateTime.now();
        log.info("main over, cost:{}", (end.getNano() - start.getNano())/1000000);
    }

    @Test
    public  void T2() throws ExecutionException, InterruptedException {
        LocalDateTime start = LocalDateTime.now();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
          List<Future<String>> list = new ArrayList<>();
          list.add(executorService.submit(new CsCallable("xxx", 5000)));
          list.add(executorService.submit(new CsCallable("www", 2000)));
          list.add(executorService.submit(new CsCallable("zzz", 14000)));
          list.add(executorService.submit(new CsCallable("yyy", 9000)));
          for (Future<String> future : list) {
                   System.out.println(future.get());
               }
        System.out.println("----- main over -----");
        LocalDateTime end = LocalDateTime.now();
        log.info("main over, cost:{}", (end.getNano() - start.getNano())/1000000);
    }

}
class CsCallable implements Callable<String> {
      private String name;
      private long milli;

         public CsCallable(String name, long milli) {
            this.name = name;
            this.milli = milli;
        }

          @Override
            public String call() throws Exception {
             System.out.println("name:" + name);
             Thread.sleep(milli);
             return name + " after " + milli + "ms call back.";
         }
 }