package com.ganht.algorithm.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the
 * absolute difference between any two elements of this subarray is less than or equal to limit.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * Example 2:
 *
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * Example 3:
 *
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    public int longestSubarray(int[] nums, int limit) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int curr_max = nums[0]; // 当子数组下最大值 这里初始化为第一个数
        int curr_min = nums[0]; // 当子数组下最大值 这里初始化为第一个数
        var sub_nums = new LinkedList<Integer>();
        for (int num : nums) {
            if (Math.abs(num - curr_max) <= limit && Math.abs(num - curr_min) <= limit && Math.abs(curr_max - curr_min) <= limit) {
                curr_max = Math.max(num, curr_max);
                curr_min = Math.min(num, curr_min);
                sub_nums.offer(num);
            } else {
                sub_nums.offer(num);
                sub_nums.poll();
                curr_max = Collections.max(sub_nums); // 当子数组最大值
                curr_min = Collections.min(sub_nums); // 当前子数组最小值
            }
        }

        return -1;

    }

}
