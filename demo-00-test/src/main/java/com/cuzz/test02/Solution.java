package com.cuzz.test02;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        deque.add(4);
        deque.add(5);
        System.out.println(deque.getFirst());
    }
    /**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0 || k < 0 || k > nums.length) {
            return res;
        }
        
        Deque<Integer> queue = new LinkedList<>(); // 保存的是下标
        for (int i = 0; i < nums.length; i++) {
            if (!queue.isEmpty() && i > queue.getLast() + k) {
               queue.removeLast();
            }
            
            while (!queue.isEmpty() && nums[i] >= nums[queue.peek()]) {
                queue.removeLast();
            } 
            queue.offer(i);
            if (i > k) {
                res.add(nums[queue.peek()]);
            }
        }
        return res;
    }
}