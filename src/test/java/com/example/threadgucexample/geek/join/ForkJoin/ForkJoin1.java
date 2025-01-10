package com.example.threadgucexample.geek.join.ForkJoin;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * 其实聊到这里，ForkJoinPool 以及 ForkJoinTask 的核心内容基本都已经介绍差不多了，而在实际的使用中，我们的一般步骤为：
 * 1、声明 ForkJoinPool 。
 * 2、继承实现 ForkJoinTask 抽象类或其子类，在其定义的方法中实现你的业务逻辑。
 * 3、子任务逻辑内部在合适的时机进行子任务的 fork 拆分。
 * 4、子任务逻辑内部在合适的时机进行 join 汇总
 * 整体上 ForkJoinPool 是对 ThreadPoolExecutor 的一种补充
 * ForkJoinPool 提供了其独特的线程工作队列绑定方式、工作分离以及窃取方式
 * ForkJoinPool + ForkJoinTask 配合实现了 Fork/Join 框架
 * 适用于任务可拆分为更小的子任务的场景（有点类似递归），适用于计算密集型任务，可以充分发挥 CPU 多核的能力
 * 作者：dying搁浅
 * 链接：https://juejin.cn/post/6990325499423621151
 * @date 2024/6/28 21:55
 */

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ForkJoin1  extends RecursiveTask<Integer> {
    private static final int THREAD_HOLD = 2;

    private int start;
    private int end;
    public ForkJoin1(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {//计算
        int sum = 0;
        //如果任务足够小就计算
        boolean canCompute = (end - start) <= THREAD_HOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            //1,2
            ForkJoin1 left = new ForkJoin1(start, middle);
            //3,4
            ForkJoin1 right = new ForkJoin1(middle + 1, end);
            //执行子任务
            left.fork();
            right.fork();
            //获取子任务结果
            int lResult = left.join();
            log.info("lResult:{}",lResult);
            int rResult = right.join();
            log.info("rResult:{}",rResult);
            sum = lResult + rResult;
            log.info("sum:{}",sum);
        }
        return sum;
    }
    //@Test
    public static void  main(String[] args){
        //真正的 Fork/Join
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoin1 task = new ForkJoin1(1, 4);
        Future<Integer> result = pool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
