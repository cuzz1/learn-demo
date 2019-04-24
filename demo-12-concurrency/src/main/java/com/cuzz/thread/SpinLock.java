package com.cuzz.thread;

import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: learn-demo
 * @description:
 * @author: cuzz
 * @create: 2019-04-24 22:59
 **/

public class SpinLock {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();
    private void lock () {
        System.out.println(Thread.currentThread() + " coming...");
        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {
            // loop
        }
    }

    private void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread + " unlock...");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLock spinLock = new SpinLock();
        new Thread(() -> {
            spinLock.lock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hahaha");
            spinLock.unlock();

        }).start();

        Thread.sleep(1);

        new Thread(() -> {
            spinLock.lock();
            System.out.println("hehehe");
            spinLock.unlock();
        }).start();
    }
}
