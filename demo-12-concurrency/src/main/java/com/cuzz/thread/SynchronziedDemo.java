package com.cuzz.thread;

/**
 * @program: learn-demo
 * @description:
 * @author: cuzz
 * @create: 2019-04-24 22:27
 **/

public class SynchronziedDemo {

    private synchronized void print() {
        doAdd();
    }
    private synchronized void doAdd() {
        System.out.println("doAdd...");
    }

    public static void main(String[] args) {
        SynchronziedDemo synchronziedDemo = new SynchronziedDemo();
        synchronziedDemo.print();
    }
}
