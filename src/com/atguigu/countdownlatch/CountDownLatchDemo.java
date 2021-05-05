package com.atguigu.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Stack
 * @date 2021-03-28 19:49
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
//        CountryEnum.FOUR.setRetCode(1);
//        System.out.println(CountryEnum.FOUR.getRetCode());
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 11");
                countDownLatch.countDown();
            },CountryEnum.foreach_Enum(i).getRetMessage()).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish");

    }

}
