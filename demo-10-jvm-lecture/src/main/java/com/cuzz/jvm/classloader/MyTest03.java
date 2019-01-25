package com.cuzz.jvm.classloader;

import java.util.UUID;

/**
 * @Author: cuzz
 * @Date: 2019/1/25 20:35
 * @Description:
 */
public class MyTest03 {
    public static void main(String[] args) {
        System.out.println(Parent3.str);
    }
}
class Parent3 {
    public static final String str = UUID.randomUUID().toString();
    static {
        System.out.println("Parent3 static block");
    }
}
