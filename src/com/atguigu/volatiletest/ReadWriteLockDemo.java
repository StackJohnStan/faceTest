package com.atguigu.volatiletest;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Stack
 * @date 2021-03-07 19:21
 */
class MyCache{
    private volatile HashMap<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" 正在写入 value 为："+key);
            map.put(key,value);
            TimeUnit.MICROSECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName()+" 写入完成 value 为："+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

    public void get(String key){
       reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" 正在读取： ");
            Object value = map.get(key);
            TimeUnit.MICROSECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName()+" 正在读取 value 为："+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }
    //模拟相当于清除缓存
    public void clear(){
        map.clear();
    }

}

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是，如果有一个线程想写共享资源类，就不应该有其他线程对该资源类进行读或写
 * 小总结：
 *          读-读能共存
 *          读-写不能共存
 *          写-写不能共存
 *
 *          写操作：原子+独占，整个过程必须是一个完整的统一体，中间不允许被分割，被打断！
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5 ; i++) {
            final Integer temp = i;
            new Thread(()->{
                myCache.put(String.valueOf(temp),temp);
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5 ; i++) {
            final Integer temp = i;
            new Thread(()->{
                myCache.get(String.valueOf(temp));
            },String.valueOf(i)).start();
        }

    }
}
