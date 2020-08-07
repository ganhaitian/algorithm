package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences
 * such that each subsequence consists of consecutive integers and has length at least 3.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 *
 * Example 2:
 *
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *
 * Example 3:
 *
 * Input: [1,2,3,4,4,5]
 * Output: False
 * @author haitian.gan
 */
public class SplitArrayIntoConsecutiveSubsequences {

    // 精妙无比的贪婪
    public boolean isPossible1(int[] nums) {
        Counter count = new Counter();
        Counter tails = new Counter();
        for (int x: nums) count.add(x, 1);

        for (int x: nums) {
            if (count.get(x) == 0) {
                continue;
            } else if (tails.get(x) > 0) {
                tails.add(x, -1);
                tails.add(x+1, 1);
            } else if (count.get(x+1) > 0 && count.get(x+2) > 0) {
                count.add(x+1, -1);
                count.add(x+2, -1);
                tails.add(x+3, 1);
            } else {
                return false;
            }
            count.add(x, -1);
        }
        return true;
    }

    static class Counter extends HashMap<Integer, Integer> {
        public int get(int k) {
            return containsKey(k) ? super.get(k) : 0;
        }

        public void add(int k, int v) {
            put(k, get(k) + v);
        }
    }


    // 审错题了
    public boolean isPossible(int[] nums) {
        TreeMap<Integer, Integer> timesMap = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            timesMap.compute(nums[i], (k, v) -> v == null ? 1 : v + 1);
        }

        while (timesMap.size() > 0) {
            int length = 0;
            Map.Entry<Integer, Integer> entry = timesMap.firstEntry();
            while (entry != null && length < 3) {
                Integer num = entry.getKey();
                timesMap.computeIfPresent(num, (k,v) -> v - 1 > 0 ? v - 1 : null);

                length++;
                entry = timesMap.higherEntry(num);
            }

            if (length < 3) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        int[] input = {1,2,3,6,2,3,4,7,8};
        System.out.println(new SplitArrayIntoConsecutiveSubsequences().isPossible(input));
    }

}
