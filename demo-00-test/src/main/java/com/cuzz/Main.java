package com.cuzz;

/**
 * @Author: cuzz
 * @Date: 2019/1/29 18:24
 * @Description:
 */

public class Main {

    final static int N = 10;

    static long a[][] = new long[N][N];

    static {
        for (int i = 0; i < N; i++) {
            a[i][(i + 1) % N] = a[(i + 1) % N][i] = 1;
        }
    }

    public static long[][] mut(int k, int n, long[][] A) {
        long[][] res = new long[n][n];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                if (i == j) {
                    res[i][j] = 1;
                } else {
                    res[i][j] = 0;
                }
            }
        }
        while (k != 0) {
            if ((k & 1) == 1) res = f(res, A);
            k >>= 1;
            A = f(A, A);
        }
        return res;
    }

    public static long[][] f(long[][] A, long[][] B) {
        long res[][] = new long[A.length][B.length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(mut(2,10,a)[0][0]);
        System.out.println(mut(3,10,a)[0][0]);
        System.out.println(mut(4,10,a)[0][0]);
        System.out.println(mut(5,10,a)[0][0]);
        System.out.println(mut(6,10,a)[0][0]);
        System.out.println(mut(7,10,a)[0][0]);
        System.out.println(mut(8,10,a)[0][0]);
    }


}