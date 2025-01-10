package com.example.threadgucexample.geek.futer.noFuterTask;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 17:18
 */
public class BasketBallJob {
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis() ;
        BasketBallBuyJob basketBallBuyJob = new BasketBallBuyJob();
        Thread bask = new Thread(basketBallBuyJob);
        bask.start();
        bask.join();  // 保证篮球包裹到达  必须要有求你才能开始
        Thread.sleep(2000);  // 寻找场地
        BaskBallSite baskBallSite =new BaskBallSite("洛克公园");
        System.out.println("寻找场地! 场地就绪"+baskBallSite.getName());

        Thread.sleep(2000);
        System.out.println("展现球季开始练习我nba的身手！");
        GoPlay(basketBallBuyJob.getBasketBall(),baskBallSite);
        long end = System.currentTimeMillis() ;
        System.out.println("时间花费 === "+  (end-start));

    }

    static void GoPlay(BasketBall basketBall , BaskBallSite baskBallSite) {

        System.out.println(basketBall+"==== start!   开玩  ====== " +baskBallSite.getName());
    }

}
