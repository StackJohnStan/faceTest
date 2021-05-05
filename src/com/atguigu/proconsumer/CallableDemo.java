package com.atguigu.proconsumer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Stack
 * @date 2021-04-05 19:47
 */

class MyThread implements Callable{

    @Override
    public Integer call() throws Exception {

        System.out.println(Thread.currentThread().getName()+"调用 callable ");
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        new Thread(futureTask,"AAA").start();
        //一个futureTask只能调用一次callable接口，如想另外调用则需要重新new 一个futureTask来完成
        new Thread(futureTask,"BBB").start();
        System.out.println(Thread.currentThread().getName()+" ----------------------");
        int result01 =100;
        //判断线程是否执行完成
        while (!futureTask.isDone()){

            System.out.println("还未计算完成");
        }
        //要求获得计算结果，如果没有得到计算结果就去强求，会导致阻塞 直到线程计算完成！！！
        Integer i = futureTask.get();
        System.out.println("result is :"+(i+result01));

    }

}
