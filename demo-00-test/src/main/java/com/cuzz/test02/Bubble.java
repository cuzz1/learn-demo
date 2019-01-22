package com.cuzz.test02;


/**
 * @Author: cuzz
 * @Date: 2018/11/28 16:38
 * @Description:
 */
public class Bubble {
    public static void main(String[] args) {
        int[] nums = {1,2,6,3,3,2};

        bubbleSort(nums);

        print(nums);
    }

    private static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j-1] > nums[j]) swap(nums, j - 1, j);
            }
            print(nums);
        }
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
