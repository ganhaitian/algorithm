package com.ganht.algorithm.leetcode;

/**
 * Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is
 * divisible by three.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,6,5,1,8]
 * Output: 18
 * Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
 * Example 2:
 *
 * Input: nums = [4]
 * Output: 0
 * Explanation: Since 4 is not divisible by 3, do not pick any number.
 * Example 3:
 *
 * Input: nums = [1,2,3,4,4]
 * Output: 12
 * Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 * @author haitian.gan
 */
public class GreatestSumDivisibleByThree {

    /**
     * 算法
     * (贪心，数学) O(n)O(n)
     * 首先求出所有数字的和，求和过程中，记录下数字模 3 余 1 的最小值和次小值，以及模 3 余 2 的最小值和次小值。
     * 如果求出的总和模 3 余 1，我们可以去掉一个模 3 余 1 的数字或者两个模 3 余 2 的数字，二者取最小去掉。
     * 如果求出的总和模 3 余 2，我们可以去掉两个模 3 余 1 的数字或者一个模 3 余 2 的数字，二者取最小去掉。
     * 如果求出的总和能被 3 整除，则直接返回。
     * @param nums
     * @return
     */
    // 仔细看条件，既然要求被3整除，这个绝对不是白给的，可以在这里面做文章
    public int maxSumDivThree(int[] nums) {
        int MAX = 100000;
        int ans = 0;
        int m1 = MAX, mn1 = MAX;
        int m2 = MAX, mn2 = MAX;

        for (int x: nums) {
            ans += x;

            if (x % 3 == 1) {
                if (x < m1) {
                    mn1 = m1;
                    m1 = x;
                } else if (x < mn1) {
                    mn1 = x;
                }
            } else if (x % 3 == 2) {
                if (x < m2) {
                    mn2 = m2;
                    m2 = x;
                } else if (x < mn2) {
                    mn2 = x;
                }
            }
        }

        if (ans % 3 == 1) return ans - Math.min(m1, m2 + mn2);
        if (ans % 3 == 2) return ans - Math.min(m1 + mn1, m2);

        return ans;
    }

}
