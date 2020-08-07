package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest
 * number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of
 * the coins, return -1.
 *
 * Example 1:
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 * @author haitian.gan
 */
public class CoinChange {

    // 缓存
    private Map<Integer, Integer> cache = new HashMap<>();
    private int rootAmount;
    private int tmpMin = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        this.rootAmount = amount;
        Arrays.sort(coins);
        if(amount == 0){
            return 0;
        }

        return doChange(coins, amount, 0);
    }

    /**
     * 优雅的动态规划，其实就是逐步通过局部最优解堆出来最终的解
     * public class Solution {
     *   public int coinChange(int[] coins, int amount) {
     *     int max = amount + 1;
     *     int[] dp = new int[amount + 1];
     *     Arrays.fill(dp, max);
     *     dp[0] = 0;
     *     for (int i = 1; i <= amount; i++) {
     *       for (int j = 0; j < coins.length; j++) {
     *         if (coins[j] <= i) {
     *           dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
     *         }
     *       }
     *     }
     *     return dp[amount] > amount ? -1 : dp[amount];
     *   }
     * }
     * @param coins
     * @param amount
     * @param times
     * @return
     */
    private int doChange(int[] coins, int amount, int times) {
        if (amount == 0) {
            return 0;
        }

        Integer cacheValue = cache.get(amount);
        if (cacheValue != null) {
            return cacheValue;
        }

        if(times > this.tmpMin){
            return -1;
        }

        int min = Integer.MAX_VALUE;
        for (int i = coins.length - 1; i >= 0; i--) {
            if (amount >= coins[i]) {
                int result = doChange(coins, amount - coins[i], times + 1);
                if (result != -1) {
                    result = result + 1;
                    if (result < min) {
                        min = result;
                    }

                    if(amount == this.rootAmount){
                        if(result < this.tmpMin){
                            this.tmpMin = result;
                        }
                    }
                }
            }
        }

        if(min == Integer.MAX_VALUE){
            min = -1;
        }

        cache.put(amount, min);
        return min;
    }

    public static void main(String[] args) {
        int[] nums = {478,487,16,338};
        System.out.println(new CoinChange().coinChange(nums, 1990));
    }

}
