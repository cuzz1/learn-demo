package com.cuzz.jvm.classloader;

/**
 * @Author: cuzz
 * @Date: 2019/1/27 22:53
 * @Description:
 */
public class MyTest15 {
    public static void main(String[] args) {
        ClassLoader loader = MyTest15.class.getClassLoader();
        System.out.println(loader);
        ClassLoader loader1 = loader.getParent();
        System.out.println(loader1);
        ClassLoader loader2 = loader1.getParent();
        System.out.println(loader2);
    }
}
