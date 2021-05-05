package com.atguigu.countdownlatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Stack
 * @date 2021-03-28 23:56
 */
public class CyclicBarrierDemo {



    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("集齐龙珠召唤神龙！！！！！！");});

        for (int i = 1; i <= 7 ; i++) {
            new Thread(()->{
                System.out.println("这是第"+Thread.currentThread().getName()+" 个龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            },String.valueOf(i)).start();
        }
    }

}
