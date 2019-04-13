package com.cuzz;

/**
 * @Author: cuzz
 * @Date: 2019/2/27 10:41
 * @Description:
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("hello");

    }
}
