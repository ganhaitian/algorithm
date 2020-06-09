package com.ganht.algorithm.leetcode;

/**
 * Given an integer array arr, in one move you can select a palindromic subarray arr[i], arr[i+1], ..., arr[j] where i <= j,
 * and remove that subarray from the given array. Note that after removing a subarray, the elements on the left and on
 * the right of that subarray move to fill the gap left by the removal.
 *
 * Return the minimum number of moves needed to remove all numbers from the array.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2]
 * Output: 2
 * Example 2:
 *
 * Input: arr = [1,3,4,1,5]
 * Output: 3
 * Explanation: Remove [4] then remove [1,3,1] then remove [5].
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= 20
 * @author haitian.gan
 */
public class PalindromeRemoval {

    /**
     * class Solution {
     * public:
     *     int minimumMoves(vector<int>& arr) {
     *         int n = arr.size();
     *         vector<vector<int>> f(n + 1, vector<int>(n + 1));
     *
     *         for (int i = 0; i <= n; i++)
     *             f[i][i] = 1;
     *
     *         for (int i = 1; i <= n; i++)
     *             f[i][i - 1] = 0;
     *
     *         for (int len = 2; len <= n; len++)
     *             for (int i = 0; i < n - len + 1; i++) {
     *                 int j = i + len - 1;
     *                 f[i][j] = 1 + f[i + 1][j];
     *                 if (arr[i] == arr[i + 1])
     *                     f[i][j] = min(f[i][j], 1 + f[i + 2][j]);
     *
     *                 for (int k = i + 2; k <= j; k++)
     *                     if (arr[i] == arr[k])
     *                         f[i][j] = min(f[i][j], f[i + 1][k - 1] + f[k + 1][j]);
     *             }
     *
     *         return f[0][n - 1];
     *     }
     * };
     *
     * @param arr
     * @return
     */
    // 又是动态规划，看来我得做个专题训练了
    public int minimumMoves(int[] arr) {
        return -1;
    }

}
