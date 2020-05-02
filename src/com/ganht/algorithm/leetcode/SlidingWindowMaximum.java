package com.ganht.algorithm.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
 * right.You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return
 * the max sliding window.
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> windowQueue = new PriorityQueue<>((a,b) -> Integer.compare(a,b) * -1);
        for(int i = 0;i < k;i ++){
            windowQueue.add(nums[i]);
        }

        List<Integer> result = new ArrayList<>();
        result.add(windowQueue.peek());
        for(int j = k;j < nums.length;j++){
            windowQueue.remove(nums[j - k]);
            windowQueue.add(nums[j]);

            result.add(windowQueue.peek());
        }

        return result.stream().mapToInt(a -> a).toArray();
    }

    // 动态规划解题
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int [] left = new int[n];
        left[0] = nums[0];
        int [] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }

    public static void main(String[] args){
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(new SlidingWindowMaximum().maxSlidingWindow(nums, 3)));
    }

}
