package com.cuzz.jvm.classloader;


/**
 * @Author: cuzz
 * @Date: 2019/1/25 14:06
 * @Description:
 */
public class MyTest01 {

    public static void main(String[] args) {
        System.out.println(Child1.str);
    }
}

class Parent1 {
    public static String str = "hello world";
    static {
        System.out.println("Parent1 static block");
    }
}

class Child1 extends Parent1 {
    public static String str2 = "welcome";
    static {
        System.out.println("Child1 static block");
    }
}
