package com.cuzz.thread;

/**
 * @Author: cuzz
 * @Date: 2019/4/17 21:34
 * @Description:
 */
public class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1;
        flag = true;
    }

    public void method02() {
        if (flag) {
            a = a + 3;
            System.out.println("a = " + a);
        }
    }

}
