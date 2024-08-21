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
 * @date 2024/6/28 18:00
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class FuterTask2 {


    @Test
  public   void  t1() throws ExecutionException, InterruptedException {
        //构造FutureTask
        FutureTask<Integer> future = new FutureTask<Integer>(new RealData(0));
        FutureTask<Integer> future1 = new FutureTask<Integer>(new RealData1(0));
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //执行FutureTask，相当于上例中的 client.request("a") 发送请求
        //在这里开启线程进行RealData的call()执行
        executor.submit(future);
        executor.submit(future1);
        System.out.println("请求数据 ========  start!");
//        try {
//            //这里依然可以做额外的数据操作，这里使用sleep代替其他业务逻辑的处理
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//        }
        //相当于上例中得data.getContent()，取得call()方法的返回值
        //如果此时call()方法没有执行完成，则依然会等待
        System.out.println("数据 = " + future.get());
        System.out.println("数据 = " + future1.get());
        executor.shutdown();
    }

}


 class RealData implements Callable<Integer> {
    private Integer para;
    public RealData(Integer para){
        this.para=para;
    }
    @Override
    public Integer call() throws Exception {

        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 200; i++) {
            para ++;
        }
        return para;
    }
}
 class RealData1 implements Callable<Integer> {
    private Integer para;
    public RealData1(Integer para){
        this.para=para;
    }
    @Override
    public Integer call() throws Exception {

        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 1000; i++) {
            para ++;
        }
        return para;
    }
}