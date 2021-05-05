package com.atguigu.test;

import com.atguigu.Singleton.Singleton6;

import java.util.concurrent.*;

/**
 * @author Stack
 * @date 2020-03-15 11:50
 */
public class TestSingleton6 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Singleton6> c = new Callable<Singleton6>() {
            @Override
            public Singleton6 call() throws Exception {
                return Singleton6.getInstance();
            }
        };

        ExecutorService ex = Executors.newFixedThreadPool(2);

        Future<Singleton6> f1 = ex.submit(c);
        Future<Singleton6> f2 = ex.submit(c);

        Singleton6 s1 = f1.get();
        Singleton6 s2 = f2.get();

        System.out.println(s1 == s2);

        ex.shutdown();

//        Singleton6 instance = Singleton6.getInstance();

    }

}
