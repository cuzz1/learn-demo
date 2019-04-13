package com.cuzz.test02;

/**
 * @Author: cuzz
 * @Date: 2019/2/18 16:50
 * @Description:
 */
public class Prime {
    public static void main(String[] args) {
        // 打印 1-100 以内的素数
        for (int i = 2; i <= 100; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    private static boolean isPrime(int i) {
        int j = 2;
        while (j < i) {
            j++;
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }
}
