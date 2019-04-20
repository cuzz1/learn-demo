package com.cuzz.thread;

/**
 * @Author: cuzz
 * @Date: 2019/4/16 21:29
 * @Description:
 */
public class VolatileDemo {
    public static void main(String[] args) {
       // test01();
       test02();
    }

    // 测试可见性
    private static void test01() {
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
            System.out.println("looping...");
        }
        System.out.println(Thread.currentThread().getName() + " job is done...");
    }

    // 测试可见性
    private static void test02() {
        Data data = new Data();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    data.addOne();
                }
            }).start();
        }
        // 默认有 main 线程和 gc 线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(data.a);
    }
}

class Data {
    volatile int a = 0;
    // int a = 0;
    void addOne() {
        this.a += 1;
    }
}
