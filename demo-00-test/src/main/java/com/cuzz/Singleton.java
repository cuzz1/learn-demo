package com.cuzz;

/**
 * @Author: cuzz
 * @Date: 2019/2/11 19:48
 * @Description:
 */
public class Singleton {

    private static volatile Singleton instance = null;

    private Singleton() {};

    public static Singleton getSingleton() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    return instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        Singleton singleton2 = Singleton.getSingleton();
        System.out.println(singleton == singleton2);

    }
}
