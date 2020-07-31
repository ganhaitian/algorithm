package com.ganht.algorithm.leetcode;

/**
 * You are given an even number of people num_people that stand around a circle and each person shakes hands with someone
 * else, so that there are num_people / 2 handshakes total.
 *
 * Return the number of ways these handshakes could occur such that none of the handshakes cross.
 *
 * Since this number could be very big, return the answer mod 10^9 + 7
 */
public class HandshakesThatDontCross {

    /**
     * 动态规划类问题。可以从上到下或者从下到上来解决这个问题。
     * 假设有n个人，数组result[i] 表示i个人构成的子问题的答案。
     * 第一个人只能和第2，4，6，8 ... 个人握手。根据握手结果将圆分成两个半球。
     * result[n] = result[0] * result[n-2] + result[2] * result[n-4] + result[4] * result[n-6] +...+ result[n-2] * result[0]
     * 循环计算的时候注意要mod 10^9 + 7
     * 这应该是分类成medium的问题吧。
     * @param num_people
     * @return
     */
    public int numberOfWays(int num_people) {
        int mod = (int) 1e9 + 7;
        int len = num_people / 2;
        long[] results = new long[len + 1];
        results[0] = 1;
        results[1] = 1;
        long result;
        for (int i = 2; i <= len; i++) {
            result = 0;
            for (int j = 1; j <= i; j++) {
                result += (results[j - 1] * results[i - j]) % mod;
                result %= mod;
            }
            results[i] = result;
        }
        return (int) results[len];
    }

}
