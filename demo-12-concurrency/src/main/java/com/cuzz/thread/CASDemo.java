package com.cuzz.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: cuzz
 * @Date: 2019/4/20 16:32
 * @Description:
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(666);
        // 获取真实值，并替换为相应的值
        boolean b = atomicInteger.compareAndSet(666, 2019);
        System.out.println(b); // true
        boolean b1 = atomicInteger.compareAndSet(666, 2020);
        System.out.println(b1); // false
        atomicInteger.getAndIncrement();
    }
}
