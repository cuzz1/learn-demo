package com.cuzz.test02;

import jdk.internal.dynalink.beans.StaticClass;

/**
 * @Author: cuzz
 * @Date: 2018/11/28 16:57
 * @Description:
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {2, 1, 3, 5, 7};
        quickSort(nums, 0 , nums.length - 1);

        print(nums);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start > end) return;
        int mid = partition(nums, start, end);
        quickSort(nums, start, mid - 1);
        quickSort(nums, mid + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        int target = nums[end];
        int p = start;
        for (int i = start; i < end; i++) {
            if (nums[i] < target) {
                swap(nums, i, p++);
            }
        }
        swap(nums, p, end);
        return p;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    private static void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
