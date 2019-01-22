package com.cuzz.test;

/**
 * @Author: cuzz
 * @Date: 2019/1/9 21:04
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        String name = "Abc.class";
        int index = name.lastIndexOf(".");
        System.out.println(index);
        System.out.println(name.substring(index + 1));
        System.out.println(Test.class.getSimpleName());
    }
}
