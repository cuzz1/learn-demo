package com.cuzz.jvm.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author: cuzz
 * @Date: 2019/2/1 14:46
 * @Description:
 */
public class MyTest26 {
    public static void main(String[] args) {
        // 把当前线程设置为扩展类加载器
        // Thread.currentThread().setContextClassLoader(MyTest26.class.getClassLoader().getParent());
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = serviceLoader.iterator();

        while(iterator.hasNext()){
            Driver driver =  iterator.next();
            System.out.println("driver: "+driver.getClass() + "loader: "+ driver.getClass().getClassLoader() );
        }

        System.out.println("当前线程上下文类加载器: " + Thread.currentThread().getContextClassLoader());

        System.out.println("ServiceLoader的类加载器: "+ServiceLoader.class.getClassLoader());
    }
}

