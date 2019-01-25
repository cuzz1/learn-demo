package com.cuzz.jvm.classloader;

/**
 * @Author: cuzz
 * @Date: 2019/1/25 23:25
 * @Description:
 */
public class MyTest04 {
    public static void main(String[] args) {
        Parent4[] parent4s = new Parent4[1];
        System.out.println("---------");
        System.out.println(parent4s.getClass());
        System.out.println(parent4s.getClass().getSuperclass());
        System.out.println("---------");
        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());
    }
}

class Parent4 {
    static {
        System.out.println("Parent4 static block");
    }
}

