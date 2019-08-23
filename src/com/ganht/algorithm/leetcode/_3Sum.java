package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Share
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets
 * in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * @author haitian.gan
 */
public class _3Sum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;

        int count = 0;
        List<List<Integer>> result = new ArrayList<>();
        while (j > i) {
            int sub = -(nums[i] + nums[j]);
            // 可以在区间里面搜索
            if (sub <= nums[j] && sub >= nums[i]) {
                int findIndex = Arrays.binarySearch(nums, i + 1, j - 1, sub);
                if (findIndex >= 0) {
                    result.add(Arrays.asList(nums[i], nums[findIndex], nums[j]));
                }

                count ++;
            } else if (sub > nums[j]) {
                i++;
            } else if (sub < nums[i]) {
                j--;
            }

            if (count % 2 == 0) {
                i ++;
            }else{
                j --;
            }
        }

        return result;
    }

    public static void main(String[] args){
        int[] input = {-1, 0, 1, 2, -1, -4};
        new _3Sum().threeSum(input);
    }

}
