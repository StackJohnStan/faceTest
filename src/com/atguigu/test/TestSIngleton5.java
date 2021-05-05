package com.atguigu.test;

import com.atguigu.Singleton.Singleton5;

import java.util.concurrent.*;

/**
 * @author Stack
 * @date 2020-03-14 22:42
 */
public class TestSIngleton5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Singleton5> c = new Callable<Singleton5>() {
            @Override
            public Singleton5 call() throws Exception {
                return  Singleton5.getInstance();
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Singleton5> f1 = executorService.submit(c);
        Future<Singleton5> f2 = executorService.submit(c);
        Singleton5 s1 = f1.get();
        Singleton5 s2 = f2.get();
        executorService.shutdown();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
    }
}
