package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in
 * range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches
 * required.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3], n = 6
 * Output: 1
 * Explanation:
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 * Example 2:
 * <p>
 * Input: nums = [1,5,10], n = 20
 * Output: 2
 * Explanation: The two patches can be [2, 4].
 * Example 3:
 * <p>
 * Input: nums = [1,2,2], n = 5
 * Output: 0
 * <p>
 * <p>
 * Created by lenovo on 2016/8/16.
 */
public class PatchingArray {

    // 这个真是巧妙，看来关键是要总结出规律
    public int minPatches1(int[] nums, int n) {
        int patches = 0, i = 0;
        long miss = 1; // use long to avoid integer overflow error
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) // miss is covered
                miss += nums[i++];
            else { // patch miss to the array
                miss += miss;
                patches++; // increase the answer
            }
        }
        return patches;
    }

    public int minPatches(int[] nums, int n) {
        var sumSet   = calSumCombination(nums, 0);
        int patchNum = 0;
        for (int i = 1; i <= n; i++) {
            if (!sumSet.contains(i)) {
                int sum        = i;
                var newSumList = new ArrayList<Integer>();
                sumSet.forEach(s -> newSumList.add(s + sum));
                sumSet.addAll(newSumList);
                sumSet.add(sum);
                patchNum++;
            }
        }

        return patchNum;
    }

    public Set<Integer> calSumCombination(int[] nums, int start) {
        if(nums.length <= 0){
            return new HashSet<>();
        }

        if (start == nums.length - 1) {
            var r = new HashSet<Integer>();
            r.add(nums[start]);
            return r;
        }

        int currVal    = nums[start];
        var result     = calSumCombination(nums, start + 1);
        var newSumList = new ArrayList<Integer>();
        result.forEach(s -> newSumList.add(s + currVal));
        result.addAll(newSumList);
        result.add(currVal);
        return result;
    }

    public static void main(String[] args){
        int[] input = {1,2,31,33};
        new PatchingArray().minPatches1(input, 2147483647);
    }

}
