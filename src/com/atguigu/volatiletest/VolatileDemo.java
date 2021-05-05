package com.atguigu.volatiletest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Stack
 * @date 2021-02-13 17:02
 */
class MyData{
    volatile int number = 0;

    public void addTo60(){
        this.number=60;
    }
    public void addPulsPuls(){
        this.number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger(0);
    public void addMyAtomicInteger(){
        atomicInteger.getAndIncrement();
    }


}

/**
 * 验证volatile的可见性
 * 1.1假如int number  =0 ;number 变量之前根本没有添加volatile关键字修饰，没有可见性
 * 1.2添加volatile 可以解决可见性问题
 * 2.验证volatile不保证原子性
 *      原子性指的什么意思？
 *      不可分割，完整性，也即某个线程正在做某个具体的业务时，中间不可以具体加塞或者分割，需要整体完整
 *      要么同时成功，要么同时失败。
 *
 *  如何解决原子性？
 *     * 加Sync
 *     * 使用我们的juc下的AtomicInteger
 */

public class VolatileDemo {

    //测试volatile不具有原子性
    @Test
    public void testVolatile(){
        MyData myData = new MyData();
        for (int i = 1; i <= 20 ; i++) {
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    myData.addPulsPuls();
                    myData.addMyAtomicInteger();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"  finaly value is "+myData.number);
        System.out.println(Thread.currentThread().getName()+"  finaly atomicInteger is "+myData.atomicInteger);
    }

    //测试volatile具有可见性
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() +"update value is "+myData.number);
        }, "AA").start();

        while(myData.number == 0){

        }
        System.out.println(Thread.currentThread().getName()+" mission is over "+myData.number);

    }

}

