package com.ganht.algorithm.leetcode;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has
 * the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubarray {

    // 这个有问题，还没有调试好
    public int maxProduct(int[] nums) {
        Integer lastNeIndex = null;
        Integer preSum = null,tmpSum = null;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] < 0){
                if(lastNeIndex == null){
                    lastNeIndex = i;
                    preSum = tmpSum;
                }else{
                    lastNeIndex = null;
                    preSum = null;
                }

                tmpSum = tmpSum == null ? nums[i] : tmpSum * nums[i];
            }else if(nums[i] == 0){
                preSum = Math.max(tmpSum,preSum);
                preSum = Math.max(preSum, 0);
                lastNeIndex = null;
                tmpSum = null;
            }else{
                tmpSum = tmpSum == null ? nums[i] : nums[i] * tmpSum;
            }
        }

        if(lastNeIndex != null){
            int afterSum = tmpSum / nums[lastNeIndex] / preSum;
            return Math.max(afterSum, preSum);
        }

        return Math.max(tmpSum, preSum);
    }

    /**
     * 这个思路非常之巧妙
     * class Solution {
     * public:
     *     int maxProduct(vector<int>& nums) {
     *         int n = nums.size();
     *         vector<int> maxTillNow(n);
     *         vector<int> minTillNow(n);
     *         maxTillNow[0] = max(0,nums[0]);
     *         minTillNow[0] = min(0,nums[0]);
     *
     *         int res = nums[0];
     *         for(int i=1;i<n;++i){
     *             if(nums[i]>0){
     *                 maxTillNow[i] = max( nums[i], maxTillNow[i-1]*nums[i] );
     *                 minTillNow[i] = min( minTillNow[i-1]*nums[i], 0 );
     *             }
     *             else{
     *                 maxTillNow[i] = max( minTillNow[i-1]*nums[i], 0);
     *                 minTillNow[i] = min( maxTillNow[i-1]*nums[i], nums[i] );
     *             }
     *
     *             res = max( res, max(minTillNow[i],maxTillNow[i]) );
     *         }
     *         return res;
     *     }
     * };
     * @param args
     */
    public static void main(String[] args){
        int[] nums = {2,3,-2,4,0,-1,2,5};
        System.out.println(new MaximumProductSubarray().maxProduct(nums));
    }

}
