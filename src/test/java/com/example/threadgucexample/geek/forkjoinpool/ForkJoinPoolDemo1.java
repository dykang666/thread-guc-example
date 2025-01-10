package com.example.threadgucexample.geek.forkjoinpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * 当一个大任务被划分成两个以上的子任务时，尽可能使用前面说到的三个衍生的invokeAll方法，因为使用它们能避免不必要的fork()。
 * 注意fork()、compute()、join()的顺序
 * @date 2024/6/29 15:40
 * 任务类必须继承RecursiveTask或者 RecursiveAction
 * 使用Fork/Join模式可以进行并行计算以提高效率
 * 这个过程非常类似于大数据处理中的 MapReduce，所以你可以把 Fork/Join 看作单机版的 MapReduce
 * cpu的核跑满，只有计算密集型能跑满cpu 如果你的计算比较小，或者不是CPU密集型的任务，不太建议使用并行处理
 */
public class ForkJoinPoolDemo1 {


    //RecursiveAction 递归动作   RecursiveTask  地柜任务
    static final class SumTask extends RecursiveTask<Integer> {
        private static final long serialVersionUID = 1L;
        final int start; //开始计算的数
        final int end; //最后计算的数

        SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            //如果计算量小于1000，那么分配一个线程执行if中的代码块，并返回执行结果
            if(end - start < 1000) {
                System.out.println(Thread.currentThread().getName() + " 开始执行: " + start + "-" + end);
                int sum = 0;
                for(int i = start; i <= end; i++)
                    sum += i;
                return sum;
            }
            //如果计算量大于1000，那么拆分为两个任务
            SumTask task1 = new SumTask(start, (start + end) / 2);
            SumTask task2 = new SumTask((start + end) / 2 + 1, end);
         // invokeAll(task1, task2);
//            //执行任务
            task1.fork();
            task2.fork();
            //获取任务执行的结果
            return task2.join() +task1.join()  ;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = new SumTask(1, 100000000);
        pool.submit(task);
        System.out.println(task.get());
        long  end = System.currentTimeMillis();
        System.out.println("任务总耗时：" + (end - start));
    }



}
