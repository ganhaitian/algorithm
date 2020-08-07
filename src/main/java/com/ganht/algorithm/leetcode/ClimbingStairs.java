package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * Created by haitian.gan on 2017/1/16.
 */
public class ClimbingStairs {

    private Map<Integer, Integer> cache = new HashMap<>();

    public int climbStairs(int n) {
        Integer cacheResult = cache.get(n);
        if(cacheResult != null){
            return cacheResult;
        }

        if(n == 0){
            return 1;
        }else if(n < 0){
            return 0;
        }else{
            int result = climbStairs(n - 1) + climbStairs(n - 2);
            cache.put(n, result);
            return result;
        }
    }

    public static void main(String[] args){
        System.out.print(new ClimbingStairs().climbStairs(44));
    }

}
