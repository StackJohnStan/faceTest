package com.atguigu.pool;

import java.util.concurrent.*;

/**
 * @author Stack
 * @date 2021-04-05 22:53
 */
public class MyThreadPoolDemo  {

    public static void main(String[] args) {

        //一池5个线程  （固定的）
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //一池一个线程
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //一池N个线程
//        ExecutorService executorService = Executors.newCachedThreadPool();

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 5, 1,TimeUnit.SECONDS, new LinkedBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());


        try {
            for (int i = 0; i <10 ; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }


    }


}
