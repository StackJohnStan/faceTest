package com.atguigu.Singleton;

/**
 * @author Stack
 * @date 2020-03-14 22:56
 */

/**
 * 在内部类被加载和初始化时才创建instance实例对象
 * 静态内部类不会自动随着外部类的加载和初始化而初始化，他是单独去加载和初始化
 * 因为是在内不类加载和初始化时创建的因此线程是安全的。
 *
 * 如果是饿汉式，枚举类最简单
 * 如果是懒汉式，静态内部类形式最简单
 */
public class Singleton6 {

    private static Singleton6 instance ;

    private Singleton6(){

    }
    private static class inner{
        private static  final Singleton6 INSTANCE = new Singleton6();
    }

    public static Singleton6 getInstance(){

        return  inner.INSTANCE;
    }
}
