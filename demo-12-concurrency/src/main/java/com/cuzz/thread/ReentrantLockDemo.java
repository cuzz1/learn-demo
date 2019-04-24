package com.cuzz.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learn-demo
 * @description:
 * @author: cuzz
 * @create: 2019-04-24 22:32
 **/

public class ReentrantLockDemo {
    private Lock lock = new ReentrantLock();

    private void print() {
        lock.lock();
        doAdd();
        lock.unlock();
    }

    private void doAdd() {
        lock.lock();
        lock.lock();
        System.out.println("doAdd...");
        lock.unlock();
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        reentrantLockDemo.print();
    }
}
