package com.cuzz.test03;

/**
 * @Author: cuzz
 * @Date: 2019/3/1 20:42
 * @Description:
 */
public class Demo {

    public static final String LEFT = "left";
    public static final String RIGHT = "right";

    static class LeftRight implements Runnable {

        @Override
        public void run() {
            synchronized (LEFT) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (RIGHT) {
                    System.out.println("xxxx");
                }
            }
        }
    }
    static class RightLeft implements Runnable {

        @Override
        public void run() {
            synchronized (RIGHT) {
                synchronized (LEFT) {
                    System.out.println("xxxx");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new LeftRight()).start();
        new Thread(new RightLeft()).start();
    }

}
