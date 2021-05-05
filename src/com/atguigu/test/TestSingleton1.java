package com.atguigu.test;

import com.atguigu.Singleton.Singleton1;

import java.net.URL;
import java.util.Properties;

/**
 * @author Stack
 * @date 2020-03-14 12:15
 */
public class TestSingleton1 {

    public static void main(String[] args) {
//        Singleton1 singleton1=Singleton1.instance;
//        System.out.println(singleton1);

        Properties pro;

        URL sf = TestSingleton1.class.getClassLoader().getResource("resource/single.propertiessingle.properties");
        System.out.println(sf);
    }

}
