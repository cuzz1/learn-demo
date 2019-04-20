package com.cuzz.thread;

/**
 * @Author: cuzz
 * @Date: 2019/4/17 20:34
 * @Description:
 */
public class ByteCodeDemo {
    volatile int n = 0;

    public void add() {
        n += 1;
    }
}
