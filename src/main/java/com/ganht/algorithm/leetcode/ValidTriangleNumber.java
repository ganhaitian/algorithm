package com.ganht.algorithm.leetcode;

import java.util.Arrays;

/**
 *  Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array
 *  that can make triangles if we take them as side lengths of a triangle.

    Example 1:

     Input: [2,2,3,4]
     Output: 3

     Explanation:
     Valid combinations are:
     2,3,4 (using the first 2)
     2,3,4 (using the second 2)
     2,2,3

     Note:
     The length of the given array won't exceed 1000.
     The integers in the given array are in the range of [0, 1000].
 *
 */
public class ValidTriangleNumber {

    /**
     * 唉，论转化问题的重要性
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) { //最短的两条边之和大于最长的边nums[i]
                    count += right - left; //[left.right-1) 区间里面的数+right都满足条件
                    right--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] input = {1,2,3,4,5,6};
        int result = new ValidTriangleNumber().triangleNumber(input);
        System.out.println(result);
    }

}
