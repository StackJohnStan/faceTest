package com.atguigu.proconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Stack
 * @date 2021-04-04 22:54
 */

class ShareData{
    private int  number = 0;
    private Lock lock  = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        try {
            lock.lock();
            //判断
            while(number != 0){
                //等待，不能生产
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + " \t" + number);
            //通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void decrement() throws InterruptedException {
        try {
            lock.lock();
            //判断
            while(number == 0){
                //等待，不能消费
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + " \t" + number);
            //通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }


}

/**
 *  一个初始值为0的变量，两个线程对其进行交替操作，一个加1一个减1 来五轮
 *
 *  1 线程  操作（方法） 资源类
 *  2 判断  干活  通知
 *  3 防止虚假唤醒机制
 *
 *
 */
public class ProconsumerTraditionDemo {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 1; i <= 5 ; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AAA").start();


        new Thread(()->{
            for (int i = 1; i <= 5 ; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB").start();

    }


}
