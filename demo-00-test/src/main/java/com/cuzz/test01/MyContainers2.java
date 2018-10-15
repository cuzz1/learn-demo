package com.cuzz.test01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: cuzz
 * @Date: 2018/10/15 13:21
 * @Description:
 */
public class MyContainers2 {

    volatile List<Object> list = new ArrayList<>();

    private int size() {
        return list.size();
    }

    public static void main(String[] args) {

        MyContainers2 containers = new MyContainers2();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() -> {
                System.out.println("Thread 2 running...");
                while (containers.size() != 5) {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Thread 2 is stop...");


        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
                System.out.println("Thread 1 running...");
                for (int i = 0; i < 10; i++) {
                    containers.list.add(new Object());
                    System.out.println(containers.size());
                    if (containers.size() == 5) {
                        countDownLatch.countDown();

                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }
        }).start();
    }

}









