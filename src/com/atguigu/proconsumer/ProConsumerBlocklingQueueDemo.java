package com.atguigu.proconsumer;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Stack
 * @date 2021-04-05 17:46
 */

class MyResource{
    private volatile boolean Falg = true; //默认开启进行生产消费
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());

    }

    public void myProd() throws InterruptedException {
        String data =null;
        boolean retValue;
        while (Falg){
            data =atomicInteger.incrementAndGet()+"";
            boolean offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if(offer){
                System.out.println(Thread.currentThread().getName()+data+"success");
            }else {
                System.out.println(Thread.currentThread().getName()+data+"default");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"stop");

    }

    public  void myConsumer() throws InterruptedException {
        String result =null;
        while (Falg){
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if(result == null || result.equalsIgnoreCase("")){
                Falg = false;
                System.out.println();
                System.out.println();
                System.out.println("没有取到，消费者退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"take "+result+"！！！");
            TimeUnit.SECONDS.sleep(1);
        }

    }

    public void stop(){
        this.Falg = false;
    }



}

public class ProConsumerBlocklingQueueDemo  {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"启动");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"pro").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"启动");
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"con").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");
        myResource.stop();

    }

}
