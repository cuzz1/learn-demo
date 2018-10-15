package com.cuzz.test01;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cuzz
 * @Date: 2018/10/15 13:21
 * @Description:
 */
public class MyContainers1 {

    volatile List<Object> list = new ArrayList<>();

    private int size() {
        return list.size();
    }

    public static void main(String[] args) {

        MyContainers1 containers = new MyContainers1();
        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 running...");
                while (containers.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
                System.out.println("Thread 2 is stop...");

            }

        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1 running...");
                for (int i = 0; i < 10; i++) {
                    containers.list.add(new Object());
                    System.out.println("1----> "+ containers.size());
                    if (containers.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

}









