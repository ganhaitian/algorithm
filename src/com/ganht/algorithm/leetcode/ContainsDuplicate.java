package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find if the array contains any duplicates.
 *
 * Your function should return true if any value appears at least twice in the array, and it should return false if every
 * element is distinct.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: true
 * Example 2:
 *
 * Input: [1,2,3,4]
 * Output: false
 * Example 3:
 *
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 * Created by lenovo on 2016/7/15.
 */
public class ContainsDuplicate {

    // 这个是借助了一个辅助的数据结构，但输入的数组超长时会超时
    public boolean containsDuplicate1(int[] nums) {
        if(nums == null || nums.length <= 1)
            return false;

        Map<Integer,Integer> tmp = new HashMap<Integer,Integer>();

        for(int i =0;i < nums.length;i++){
            if(tmp.containsKey(nums[i]))
                return true;
            else{
                tmp.put(nums[i],1);
            }
        }

        return false;
    }

    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length <= 1)
            return false;

        // 先排序
        Arrays.sort(nums);
        for(int i = 0;i < nums.length - 1;i++){
            if(nums[i] == nums[i + 1])
                return true;
        }

        return false;
    }

    public static void main(String[] args){
        int[] input = new int[30000];
        for(int i =0;i < 30000;i++){
            input[i] = i;
        }

        long start = System.currentTimeMillis();
        System.out.println(new ContainsDuplicate().containsDuplicate1(input));
        System.out.println(System.currentTimeMillis() - start);
    }
}
