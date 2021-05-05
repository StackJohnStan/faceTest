package com.atguigu.volatiletest;

/**
 * @author Stack
 * @date 2021-03-08 0:33
 */


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁（也叫递归锁）
 * 指的是同一线程外层获得锁之后，内层递归函数仍能获取该锁的代码
 * 在同一线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 * 也就是说，线程可以进入任何一个他它已经拥有锁的同步代码块
 *
 *  Synchronized 和 ReentranLock  都是可重入锁
 */

class Phone implements  Runnable
{
    ReentrantLock reentrantLock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    public void get(){
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName()+" invode get method");
        try {
            TimeUnit.MICROSECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        set();
        reentrantLock.unlock();
    }

    public void set(){
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName()+" invode set method");
        reentrantLock.unlock();
    }

}

public class ReenterLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Thread t1 = new Thread(phone,"t1");
        Thread t2 = new Thread(phone, "t2");
        t1.start();
        t2.start();
    }

}
