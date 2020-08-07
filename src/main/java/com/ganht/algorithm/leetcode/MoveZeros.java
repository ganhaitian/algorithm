package com.ganht.algorithm.leetcode;

/**
 *
 *   Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

     For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

     Note:
     You must do this in-place without making a copy of the array.
     Minimize the total number of operations.
 *
 * Created by haitian.gan on 2016/11/28.
 */
public class MoveZeros {

    public void moveZeroes(int[] nums) {
        int zeroNums = 0;
        int tailIndex = nums.length - 1;
        for(int i = nums.length - 1;i >= 0;i --){
            if(nums[i] == 0){
                System.arraycopy(nums,i + 1,nums,i,tailIndex - zeroNums - i);
                nums[tailIndex - zeroNums] = 0;
                zeroNums ++;
            }
        }
    }

    public static void main(String[] args){
        new MoveZeros().moveZeroes(new int[]{0});
    }
}
