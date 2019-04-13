package com.cuzz.jvm.classloader;

/**
 * @Author: cuzz
 * @Date: 2019/1/27 16:23
 * @Description:
 */
public class MyTest12 {
    public static void main(String[] args) throws Exception{
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = classLoader.loadClass("com.cuzz.jvm.classloader.CL");
        System.out.println("--------------");
        clazz = Class.forName("com.cuzz.jvm.classloader.CL");
    }
}

class CL {
    static {
        System.out.println("CL static block");
    }
}
