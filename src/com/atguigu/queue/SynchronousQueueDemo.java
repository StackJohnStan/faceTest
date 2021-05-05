package com.atguigu.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Stack
 * @date 2021-04-04 12:34
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        //非公平
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + " \t put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName() + " \t put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName() + " \t put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"AAA").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(synchronousQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(synchronousQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"BBB").start();

    }

}
