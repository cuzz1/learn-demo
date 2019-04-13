package com.cuzz.test02;

/**
 * @Author: cuzz
 * @Date: 2019/2/18 16:18
 * @Description:
 */
public class Quick {
    public static void main(String[] args) {

        int [] nums = {2, 3, 7, 5, 1};

        quicksort(nums, 0, nums.length - 1);
        for (int n : nums) {
            System.out.print(n + " ");
        }
    }

    private static void quicksort(int[] nums, int start, int end) {
        if (start > end) return;
        int mid = partition(nums, start, end);
        quicksort(nums, start, mid - 1);
        quicksort(nums, mid + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        int m = nums[end];
        int p = 0;
        for (int i = 0; i < end; i++) {
            if (nums[i] < m) {
                exchange(nums, i , p++);
            }
        }
        exchange(nums, p, end);
        return p;
    }

    private static void exchange(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
