package com.cuzz.jvm.classloader;

/**
 * @Author: cuzz
 * @Date: 2019/1/25 19:16
 * @Description:
 */
public class MyTest02 {
    public static void main(String[] args) {
        // System.out.println(Parent2.str);
        System.out.println(Parent2.i);
    }
}
class Parent2 {
    public static final String str = "hello world";
    public static final short i = 20;
    static {
        System.out.println("Parent2 static block");
    }
}
