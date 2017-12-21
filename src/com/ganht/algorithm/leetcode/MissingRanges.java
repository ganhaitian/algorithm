package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
