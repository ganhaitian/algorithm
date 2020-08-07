package com.ganht.algorithm.leetcode;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of
 * which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * Example:
 *
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 * @author haitian.gan
 */
public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int s, int[] nums) {
        int result = Integer.MAX_VALUE;
        int lastLength = 0,lastSum = 0;
        for(int i = 0;i < nums.length - 1;i++){
            if(lastLength > 0){
                lastSum = lastSum - nums[i - 1];
            }

            if(lastSum >= s){
                int min = lastLength - i;
                if(min < result){
                    result = min;
                }
            }else{
                boolean reach = false;
                for(int j = lastLength;j < nums.length;j++){
                    lastLength ++;
                    lastSum += nums[j];

                    if(lastSum >= s){
                        int min = lastLength - i;
                        if(min < result){
                            result = min;
                        }
                        reach = true;
                        break;
                    }
                }

                if(!reach){
                    break;
                }
            }
        }

        if(result == Integer.MAX_VALUE){
            return 0;
        }

        return result;
    }

    public static void main(String[] args){
        int[] nums = {2,3,1,2,4,3};
        new MinimumSizeSubarraySum().minSubArrayLen(7, nums);
    }

}
