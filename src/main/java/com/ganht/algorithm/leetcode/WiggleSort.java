package com.ganht.algorithm.leetcode;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * <p>
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 *
 * <p>
 * Note:
 * You may assume all input has valid answer.
 *
 * <p>
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 *
 * <p>
 * Credits:
 * Special thanks to @dietpepsi for adding this problem and creating all test cases.
 *
 * Created by haitian.gan on 2017/3/16.
 */
public class WiggleSort {

    // 为了使结构稳定的保证是一对一对的，可以联想到是有序数组的等差对儿。
    public void wiggleSort(int[] nums){
        Arrays.sort(nums);

        int n = nums.length;
        int j = n;
        int k = (n + 1) / 2;
        int[] result = new int[n];

        for(int i =  0;i < n;i ++){
            result[i] = (i % 2 == 0) ? nums[--k] : nums[--j];
        }

        System.arraycopy(result,0,nums,0,nums.length);
    }

    /*public void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        int[] result = new int[nums.length];
        int[] flags = new int[nums.length];
        result[0] = nums[0];
        flags[0] = 1;

        int currIndex = 0;
        int lastIndex = 0;
        int visitStart = 0;
        int offset = 0;
        for(int i = 1;i < nums.length;i++){
            if(flags[i] == 1){
                offset++;
                continue;
            }

            if(i - lastIndex - offset <= 1)
                continue;

            if(nums[i] <= nums[lastIndex])
                continue;

            flags[i] = 1;
            result[++currIndex] = nums[i];

            for(int j = visitStart;j < i;j ++){
                if(flags[j] == 1)
                    continue;

                result[++currIndex] = nums[j];
                flags[j] = 1;
                lastIndex = j;
                visitStart = j + 1;
                i = j;
                break;
            }

            offset = 0;
        }

        for(int k = visitStart;k <= nums.length -1;k ++){
            if(flags[k] == 0 && k == nums.length - 1){
                flags[k] = 1;
                result[nums.length - 1] = nums[nums.length - 1];
            }
        }

        System.arraycopy(result,0,nums,0,nums.length);
    }

    */

    public static void main(String[] args){
        new WiggleSort().wiggleSort(new int[]{2,1,3});
    }

}
