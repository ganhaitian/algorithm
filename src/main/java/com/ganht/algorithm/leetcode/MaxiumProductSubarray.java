package com.ganht.algorithm.leetcode;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * For example, given the array [2,3,-2,4],
   the contiguous subarray [2,3] has the largest product = 6.
 *
 *
 * Created by lenovo on 2016/7/15.
 */
public class MaxiumProductSubarray {

    public int maxProduct(int[] nums) {
        int maxProduct = 0;
        int tmpProduct = 1;
        for(int i =0;i < nums.length;i++){
            if(nums[0] > 0)
                tmpProduct = tmpProduct * i;
            else
                tmpProduct = i;

            if(tmpProduct >= maxProduct)
                maxProduct = tmpProduct;

        }

        return maxProduct;
    }

    public static void main(String[] args){
        
    }
}
