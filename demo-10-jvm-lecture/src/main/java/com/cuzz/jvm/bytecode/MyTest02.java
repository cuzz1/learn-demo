package com.cuzz.jvm.bytecode;

/**
 * @Author: cuzz
 * @Date: 2019/3/4 13:34
 * @Description:
 */
public class MyTest02 {
    String lock = "lock";
    int x = 0;

    public synchronized void setX(int x) {
        this.x = x;
    }

    public int getX() {
        synchronized (lock) {
            return x;
        }
    }

    public static void main(String[] args) {
        MyTest02 test = new MyTest02();
        test.setX(10);
    }
}
