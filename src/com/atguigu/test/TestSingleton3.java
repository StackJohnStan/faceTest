package com.atguigu.test;

import com.atguigu.Singleton.Singleton3;

/**
 * @author Stack
 * @date 2020-03-14 12:40
 */
public class TestSingleton3 {

    public static void main(String[] args) {
        Singleton3 instance = Singleton3.instance;
        System.out.println(instance);
    }
}
