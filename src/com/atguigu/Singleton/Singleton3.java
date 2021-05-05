package com.atguigu.Singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Stack
 * @date 2020-03-14 12:34
 */
public class Singleton3 {

    public  static final Singleton3 instance;

    private String info;

    static{
        //用类加载器加载类路径下的资源
        Properties pro = new Properties();
        try {
            pro.load(Singleton3.class.getClassLoader().getResourceAsStream("resource/single.properties"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        String info1 = pro.getProperty("info");
        System.out.println(info1);
        instance = new Singleton3(pro.getProperty("info"));
    }

    private Singleton3(String info){
        this.info=info;
    }

    @Override
    public String toString() {
        return "Singleton3{" +
                "info='" + info + '\'' +
                '}';
    }
}
