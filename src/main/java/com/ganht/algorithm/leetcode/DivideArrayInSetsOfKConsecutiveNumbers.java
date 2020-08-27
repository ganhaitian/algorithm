package com.ganht.algorithm.leetcode;

import java.util.TreeMap;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets
 * of k consecutive numbers
 * Return True if its possible otherwise return False.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 * Example 2:
 *
 * Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * Output: true
 * Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
 * Example 3:
 *
 * Input: nums = [3,3,2,2,1,1], k = 3
 * Output: true
 * Example 4:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * Explanation: Each array should be divided in subarrays of size 3.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 * Note: This question is the same as 846: https://leetcode.com/problems/hand-of-straights/
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {

    public boolean isPossibleDivide(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num :nums){
            map.merge(num, 1, (oldValue , value) -> oldValue + 1);
        }

        while (map.size() > 0) {
            Integer tmpKey = map.firstKey();
            for (int i = 0; i < k; i++) {
                if (!map.containsKey(tmpKey)) {
                    return false;
                }

                map.computeIfPresent(tmpKey, (oldKey, oldValue) -> {
                    int newValue = oldValue - 1;
                    return newValue <= 0 ? null : newValue;
                });

                tmpKey = tmpKey + 1;
            }
        }

        return true;
    }

    public static void main(String[] args){
        int[] nums = {2,4,6};
        System.out.println(new DivideArrayInSetsOfKConsecutiveNumbers().isPossibleDivide(nums, 3));
    }

}
