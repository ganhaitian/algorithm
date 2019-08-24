package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper],
 * return its missing ranges.
 *
 * Example:
 *
 * Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 * Output: ["2", "4->49", "51->74", "76->99"]
 *
 */
public class MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {

        if(nums.length == 0){
            return Arrays.asList(lower + "->" + upper);
        }

        List<String> range = new ArrayList<>();
        if(nums.length == 1){
            if(nums[0] > lower){

            }
        }

        return null;
    }

}
