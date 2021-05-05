package com.atguigu.countdownlatch;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Stack
 * @date 2021-03-29 0:24
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore  semaphore = new Semaphore(3);
        for (int i = 1; i <=7 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println("抢到"+Thread.currentThread().getName()+"个车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }

}
