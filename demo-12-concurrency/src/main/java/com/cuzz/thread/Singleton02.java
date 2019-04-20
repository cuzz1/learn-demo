package com.cuzz.thread;

import com.cuzz.annotation.NotThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: cuzz
 * @Date: 2019/4/20 13:56
 * @Description:
 */
@NotThreadSafe
public class Singleton02 {
    private static volatile Singleton02 instance = null;
    private Singleton02() {
        System.out.println(Thread.currentThread().getName() + "  construction...");
    }
    public static Singleton02 getInstance() {
        if (instance == null) {
            synchronized (Singleton01.class) {
                if (instance == null) {
                    instance = new Singleton02();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(()-> Singleton02.getInstance());
        }
        executorService.shutdown();
    }
}
