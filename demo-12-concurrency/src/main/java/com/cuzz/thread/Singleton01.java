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
public class Singleton01 {
    private static Singleton01 instance = null;
    private Singleton01() {
        System.out.println(Thread.currentThread().getName() + "  construction...");
    }
    public static Singleton01 getInstance() {
        if (instance == null) {
            instance = new Singleton01();
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(()-> Singleton01.getInstance());
        }
        executorService.shutdown();
    }
}
