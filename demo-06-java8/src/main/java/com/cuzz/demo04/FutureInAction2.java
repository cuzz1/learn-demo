package com.cuzz.demo04;


import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: cuzz
 * @Date: 2018/10/14 17:56
 * @Description:
 */
public class FutureInAction2 {


    public static void main(String[] args) {

        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000L);
                return "I am finished.";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return e.toString();
            }
        });

        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable throwable) {
                System.out.println("Error");
                throwable.printStackTrace();
            }
        });
        System.out.println(future.get());
        System.out.println("Do some other things.");

    }


    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> reference = new AtomicReference<>();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);


        Future<T> future = new Future<T>() {

            private Completable<T> completable;

            @Override
            public T get() {
                return reference.get();
            }

            @Override
            public boolean isDone() {
                return atomicBoolean.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                return completable;
            }
        };


        Thread thread = new Thread(() -> {
            try {
                T t = callable.action();
                reference.set(t);
                atomicBoolean.set(true);
                if (future.getCompletable() != null) {
                    future.getCompletable().complete(t);
                }
            } catch (Throwable throwable) {
                future.getCompletable().exception(throwable);
            }

        });
        thread.start();

        return future;

    }

    private interface Future<T> {
        T get();

        boolean isDone();

        void setCompletable(Completable<T> completable);

        Completable<T> getCompletable();
    }

    private interface Callable<T> {
        T action();
    }

    private interface Completable<T> {
        void complete(T t);

        void exception(Throwable throwable);
    }




}
