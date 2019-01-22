package com.cuzz.test02;

/**
 * @Author: cuzz
 * @Date: 2018/11/8 10:47
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Integer a = 331;
        Integer b = 331;
        System.out.println(a == b);
    }

    private void a() {
        b();
        System.out.println("aaa");
    }

    private void b() {
        System.out.println("bbb");
    }








}
