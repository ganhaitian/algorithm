package com.ganht.algorithm.leetcode;

/**
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous
 * subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 *
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 *
 *
 * Note:
 *
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 * @author haitian.gan
 */
public class ContinuousSubarraySum {

    private static class CheckResult {
        private boolean success;
        private int     sum;
    }

    // 这他妈得用点儿数学知识推导出个公式，这破题肯定是中国人提交的
    public boolean checkSubarraySum(int[] nums, int k) {
        CheckResult result = doCheckSubArraySum(nums, 0 , nums.length - 1, k);
        if(result.success){
            return true;
        }

        int[] reverseNums = reverse(nums);
        result = doCheckSubArraySum(reverseNums, 0, nums.length - 1, k);
        return result.success;
    }

    public int[] reverse(int[] nums){
        int[] result = new int[nums.length];
        for(int i = 0;i < nums.length;i++){
            result[i] = nums[nums.length - 1 - i];
        }

        return result;
    }

    private boolean checkRemainder(int sum, int k){
        if(k == 0){
            return sum == 0;
        }else{
            return sum % k == 0;
        }
    }

    private CheckResult doCheckSubArraySum(int[] nums, int start, int end, int k) {
        CheckResult result = new CheckResult();
        if (start == end) {
            result.sum = nums[end];
            result.success = false;
            return result;
        }


        if (checkRemainder(nums[start] + nums[start + 1], k)) {
            result.success = true;
        } else {
            CheckResult behindResult = doCheckSubArraySum(nums, start + 1, end, k);
            if (!behindResult.success) {
                if (checkRemainder(nums[start] + behindResult.sum, k)) {
                    result.success = true;
                } else {
                    result.success = false;
                    result.sum = nums[start] + behindResult.sum;
                }
            } else {
                result.success = true;
            }
        }

        return result;
    }

    public static void main(String[] args){
        int[] nums = {470,161,377,184,118,91,413,73,224,167,505,413,521,5,7,372,393,523,211,479,90,516,238,467,410,51,337,31,442,297,100,75,260,33,490,477,21,374,233,41,215,87,84,153,271,241,169,275,323,358,291,176,423,426,296,175,413,423,387,416,27,266,257,136,27,155,77,142,60,335,401,443,52,153,345,71,117,443,177,238,433,464,323,79,156,429,79,327,64,335,92,147,350,480,277,335,97,317,420,453};
        new ContinuousSubarraySum().checkSubarraySum(nums, 75);
    }

}
