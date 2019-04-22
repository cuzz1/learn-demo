package com.cuzz.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: learn-demo
 * @description: ABA
 * @author: cuzz
 * @create: 2019-04-21 23:31
 **/

public class ABADemo {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    public static void main(String[] args) {
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }).start();

        new Thread(() -> {
            // 保证上面线程先执行
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet(100, 2019);
            System.out.println(atomicReference.get()); // 2019
        }).start();
    }
}
