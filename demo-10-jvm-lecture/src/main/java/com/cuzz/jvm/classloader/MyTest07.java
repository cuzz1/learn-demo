package com.cuzz.jvm.classloader;

/**
 * @Author: cuzz
 * @Date: 2019/1/26 20:05
 * @Description:
 */
public class MyTest07 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader());

        Class<?> clazz1 = Class.forName("com.cuzz.jvm.classloader.C");
        System.out.println(clazz1.getClassLoader());
    }
}

class C {

}
