package com.atguigu.test;

import com.atguigu.Singleton.Singleton2;

/**
 * @author Stack
 * @date 2020-03-14 12:25
 */
public class TestSingleton2 {

    public static void main(String[] args) {
        Singleton2 instance = Singleton2.instance;
        System.out.println(instance);
    }
}
