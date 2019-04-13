package com.cuzz.jvm.classloader;


/**
 * @Author: cuzz
 * @Date: 2019/1/27 15:22
 * @Description:
 */
public class MyTest09 {
    static {
        System.out.println("MyTest09 static block");
    }
    public static void main(String[] args) {
        Parent9 parent9;     // 不会初始化
        System.out.println("-------------");
        parent9 = new Parent9();
        System.out.println("-------------");
        System.out.println(Parent9.i);
        System.out.println("-------------");
        System.out.println(Child9.j);

    }
}

class Parent9 {
    public static int i = 9;
    static {
        System.out.println("Parent9 static block");
    }
}

class Child9 extends Parent9 {
    public static int j = 99;
    static {
        System.out.println("Child9 static block");
    }
}


