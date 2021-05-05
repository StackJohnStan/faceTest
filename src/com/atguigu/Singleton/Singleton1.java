package com.atguigu.Singleton; /**
 * @author Stack
 * @date 2020-03-14 11:43
 */

/**
 * 饿汉式：
 * 在类初始化时直接创建实例对象，不管你是否需要这个对象都要创建
 *
 * 1，构造器私有化
 * 2，自行创建，并且用静态变量保存
 * 3，向外提供这个实例
 * 4，强调这是一个单例可以用final修饰
 */
public class Singleton1 {

    public static final Singleton1 instance = new Singleton1();

    private Singleton1(){

    }
}
