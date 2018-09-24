package com.cuzz.bean;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 21:20
 * @Description:
 */
public class Car {

    public Car () {
        System.out.println("car constructor...");
    }

    public void init() {
        System.out.println("car...init...");
    }

    public void destroy() {
        System.out.println("car...destroy...");
    }
}
