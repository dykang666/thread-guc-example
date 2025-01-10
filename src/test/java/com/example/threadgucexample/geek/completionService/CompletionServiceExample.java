package com.example.threadgucexample.geek.completionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author kangdongyang
 * @version 1.0  批量执行异步任务
 * @description:当我们使用ExecutorService启动多个Callable时，每个Callable返回一个Future，而当我们执行Future的get方法获取结果时，
 * 可能拿到的Future并不是第一个执行完成的Callable的Future，就会进行阻塞，从而不能获取到第一个完成的Callable结果，那么这样就造成了很严重的性能损耗问题。
 * 而CompletionService正是为了解决这个问题，它是Java8的新增接口，它的实现类是ExecutorCompletionService。
 * CompletionService会根据线程池中Task的执行结果按执行完成的先后顺序排序，任务先完成的可优先获取到。
 * 原文链接：https://blog.csdn.net/liuyu973971883/article/details/108055564
 * @date 2024/7/4 21:49
 */
public class CompletionServiceExample {

        public static void main(String[] args) throws InterruptedException, ExecutionException {
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            List<Callable<Integer>> callables = Arrays.asList(
                    ()->{
                        mySleep(20);
                        System.out.println("=============20 end==============");
                        return 20;
                    },
                    ()->{
                        mySleep(10);
                        System.out.println("=============10 end==============");
                        return 10;
                    }
            );
            List<Future<Integer>> futures = new ArrayList<>();
            //提交任务,并将future添加到list集合中
            futures.add(executorService.submit(callables.get(0)));
            futures.add(executorService.submit(callables.get(1)));
            //遍历Future,因为不知道哪个任务先完成,所以这边模拟第一个拿到的就是执行时间最长的任务,那么执行时间较短的任务就必须等待执行时间长的任务执行完
            for (Future future:futures) {
                System.out.println("结果: "+future.get());
            }
            System.out.println("============main end=============");
        }
        private static void mySleep(int seconds){
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


}
