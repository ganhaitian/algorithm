package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
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
