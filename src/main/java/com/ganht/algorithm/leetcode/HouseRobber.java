package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and
 * it will automatically contact the police if two adjacent houses were broken into on the same night.

   Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
   money you can rob tonight without alerting the police.

   Credits:
   Special thanks to @ifanchu for adding this problem and creating all test cases. Also thanks to @ts for adding additional
   test cases.
 */
public class HouseRobber {

    private Map<Integer,Integer> cache = new HashMap<>();

    /**
     * 为什么我总是想的这么的复杂
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int most = 0;
        for(int i = 0;i < nums.length;i++){
            int amount = nums[i] + selectHouse(i,nums);
            if(most < amount){
                most = amount;
            }
        }

        return most;
    }

    private int selectHouse(int start,int[] nums){
        int most = 0;
        for (int i = start + 2; i < nums.length; i++) {
            Integer existMost = cache.get(i);
            int ammount = nums[i] + ( existMost == null ? selectHouse(i,nums) : existMost );
            if(most < ammount){
                most = ammount;
            }
        }

        cache.put(start, most);
        return most;
    }

    public static void main(String[] args){
        System.out.println(new HouseRobber().rob(new int[]{10,4,5,7,2}));
    }

}
