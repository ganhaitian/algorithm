package com.ganht.algorithm.leetcode;

import java.util.Arrays;

/**
 * Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.
 * <p>
 * Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 * Example 2:
 * <p>
 * Input: nums = [1,5,0,10,14]
 * 1,5,0,10,14,12
 * 0,1,5,10,12,14
 * <p>
 * Output: 1
 * Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
 * The difference between the maximum and minimum is 1-0 = 1.
 * Example 3:
 * <p>
 * Input: nums = [6,6,0,1,1,4,6]
 * 0,1,1,4,6,6,6
 * Output: 2
 * Example 4:
 * <p>
 * Input: nums = [1,5,6,14,15]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {
    
    public int minDifference(int[] nums) {
        if (nums.length <= 4) {
            return 0;
        }
        
        Arrays.sort(nums);
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= 3; i++) {
            res = Math.min(res, nums[nums.length - 1 - i] - nums[3 - i]);
        }
        
        return res;
    }
    
}
