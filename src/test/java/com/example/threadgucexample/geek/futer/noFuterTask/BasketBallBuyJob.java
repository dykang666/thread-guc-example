package com.example.threadgucexample.geek.futer.noFuterTask;

/**
 * @author kangdongyang
 * @version 1.0
 * @description:
 * @date 2024/6/28 17:16
 */
public class BasketBallBuyJob   implements Runnable{
    private BasketBall basketBall ;

    @Override
    public void run() {
        basketBall =new BasketBall();
        System.out.println(basketBall);
        System.out.println("下单");
        System.out.println("等待收揽包裹！");
        try {
            Thread.sleep(5000);
            System.out.println("快递出发！"+"basketBall 已经到达");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public BasketBall getBasketBall() {
        return basketBall;
    }

    public void setBasketBall(BasketBall basketBall) {
        this.basketBall = basketBall;
    }

}
