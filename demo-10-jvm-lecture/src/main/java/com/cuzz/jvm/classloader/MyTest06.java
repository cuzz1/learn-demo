package com.cuzz.jvm.classloader;

/**
 * @Author: cuzz
 * @Date: 2019/1/26 11:09
 * @Description:
 */
public class MyTest06 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.newSingleton();
        System.out.println(Singleton.counter1);
        System.out.println(Singleton.counter2);
    }
}

class Singleton {

    public static int counter1;

    private static Singleton singleton = new Singleton();

    private Singleton() {
        counter1++;
        counter2++;
    }

    public static int counter2 = 0;

    public static Singleton newSingleton() {
        return singleton;
    }
}
