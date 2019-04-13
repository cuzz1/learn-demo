package com.cuzz.jvm.classloader;


/**
 * @Author: cuzz
 * @Date: 2019/1/26 10:38
 * @Description:
 */
public class MyTest05 {
    public static void main(String[] args) {
        System.out.println(Child5.j);
    }
}
interface Parent5 {
    public static Thread thread = new Thread() {
        {
            System.out.println("Parent5 static block");
        }
    };
}

class Child5 implements Parent5 {
    public static int j = 55;
}
