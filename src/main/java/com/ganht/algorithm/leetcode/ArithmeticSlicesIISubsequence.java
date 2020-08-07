package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any
 * two consecutive elements is the same.
 *
 * For example, these are arithmetic sequences:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 *
 * 1, 1, 2, 5, 7
 *
 * A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers
 * (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.
 *
 * A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk]
 * is arithmetic. In particular, this means that k ≥ 2.
 *
 * The function should return the number of arithmetic subsequence slices in the array A.
 *
 * The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed
 * to be less than 231-1.
 *
 *
 * Example:
 *
 * Input: [2, 4, 6, 8, 10]
 *
 * Output: 7
 *
 * Explanation:
 * All arithmetic subsequence slices are:
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 */
public class ArithmeticSlicesIISubsequence {

    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        long ans = 0;
        Map<Integer, Integer>[] cnt = new Map[n];
        for (int i = 0; i < n; i++) {
            cnt[i] = new HashMap<>(i);
            for (int j = 0; j < i; j++) {
                long delta = (long)A[i] - (long)A[j];
                if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }
                int diff = (int)delta;
                int sum = cnt[j].getOrDefault(diff, 0);
                int origin = cnt[i].getOrDefault(diff, 0);
                cnt[i].put(diff, origin + sum + 1);
                ans += sum;
            }
        }
        return (int)ans;
    }

    private int n;
    private int ans;
    private void dfs(int dep, int[] A, List<Long> cur) {
        if (dep == n) {
            if (cur.size() < 3) {
                return;
            }
            long diff = cur.get(1) - cur.get(0);
            for (int i = 1; i < cur.size(); i++) {
                if (cur.get(i) - cur.get(i - 1) != diff) {
                    return;
                }
            }
            ans ++;
            return;
        }
        dfs(dep + 1, A, cur);
        cur.add((long)A[dep]);
        dfs(dep + 1, A, cur);
        cur.remove((long)A[dep]);
    }

    public int numberOfArithmeticSlices1(int[] A) {
        n = A.length;
        ans = 0;
        List<Long> cur = new ArrayList<Long>();
        dfs(0, A, cur);
        return (int)ans;
    }

    public static void main(String[] args){
        int[] input = {2, 4, 6, 8, 10};
        new ArithmeticSlicesIISubsequence().numberOfArithmeticSlices1(input);
    }

}
