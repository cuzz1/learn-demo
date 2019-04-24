package com.cuzz.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learn-demo
 * @description:
 * @author: cuzz
 * @create: 2019-04-23 23:40
 **/

public class NotReentrantLock {
    private boolean isLocked = false;
    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }
    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
