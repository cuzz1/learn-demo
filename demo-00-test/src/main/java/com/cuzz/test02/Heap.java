package com.cuzz.test02;


import javax.xml.bind.SchemaOutputResolver;

/**
 * @Author: cuzz
 * @Date: 2018/11/23 18:36
 * @Description:
 */
public class Heap {
    public static void main(String[] args) {
        int[] array = {2, 3,  0, 1, 6, 4, 5};

        heapSort(array);

    }

    private static void printArr(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    private static void heapSort(int[] array) {
        int len = array.length - 1;
        for (int i = (len - 1) / 2 ; i >= 0; i--) {
            sink(array, i, len);
        }
        printArr(array);
        while (len >= 0) {
            swap(array, 0, len);
            sink(array, 0, --len);
        }
        printArr(array);
    }

    private static void sink(int[] array, int i, int len) {
        while (i * 2 + 1 <= len) {
            int j = i * 2 + 1;
            if (j < len && array[j + 1] > array[j]) j++;
            swap(array, i, j);
            i = j;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
