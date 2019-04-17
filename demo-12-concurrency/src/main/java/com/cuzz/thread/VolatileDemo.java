package com.cuzz.thread;

/**
 * @Author: cuzz
 * @Date: 2019/4/16 21:29
 * @Description:
 */
public class VolatileDemo {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " coming...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addOne();
            System.out.println(Thread.currentThread().getName() + " updated...");
        }).start();

        while (data.a == 0) {
            // looping
        }
        System.out.println(Thread.currentThread().getName() + " job is done...");
    }
}

class Data {
    volatile int a = 0;
    void addOne() {
        this.a += 1;
    }
}
