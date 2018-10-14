package com.cuzz.demo04;


import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: cuzz
 * @Date: 2018/10/14 17:56
 * @Description:
 */
public class FutureInAction {


    @Test
    public void test01() throws InterruptedException {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(100L);
                return "I am finished.";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return e.toString();
            }
        });
        System.out.println(future.get());
        while (!future.isDone()) {
            Thread.sleep(10);
        }
        System.out.println(future.get());

    }


    private <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> reference = new AtomicReference<>();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        Thread thread = new Thread(() -> {
            T t = callable.action();
            reference.set(t);
            atomicBoolean.set(true);

        });
        thread.start();


        Future<T> future = new Future<T>() {
            @Override
            public T get() {
                return reference.get();
            }

            @Override
            public boolean isDone() {
                return atomicBoolean.get();
            }
        };

        return future;

    }

    private interface Future<T> {
        T get();
        boolean isDone();
    }

    private interface Callable<T> {
        T action();
    }


}
