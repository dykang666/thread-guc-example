package com.example.threadgucexample.geek.futer.futer2;

import java.util.concurrent.Callable;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 17:01
 */
public class BasketBallBuyFutureJob  implements Callable<BasketBallFuture> {
    @Override
    public BasketBallFuture call() throws Exception {
        BasketBallFuture basketBallFuture =new BasketBallFuture();
        System.out.println(basketBallFuture);
        System.out.println("下单");
        System.out.println("等待收揽包裹！");
        try {
            Thread.sleep(100);
            System.out.println("快递出发！"+"basketBall 已经到达");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return basketBallFuture ;
    }
}
