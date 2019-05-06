package com.cuzz.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @program: learn-demo
 * @description:
 * @author: cuzz
 * @create: 2019-05-06 23:43
 **/

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        DeadLockDemo deadLockDemo = new DeadLockDemo();
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> deadLockDemo.method(lockA, lockB));
        executor.execute(() -> deadLockDemo.method(lockB, lockA));

    }

    public void method(String lock1, String lock2) {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "--获取到：" + lock1 + "; 尝试获取：" + lock2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println("获取到两把锁!");
            }
        }
    }
}

