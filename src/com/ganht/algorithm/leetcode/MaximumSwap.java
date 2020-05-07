package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the
 * maximum valued number you could get.
 *
 * Example 1:
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * Example 2:
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 * Note:
 * The given number is in the range [0, 108]
 * @author haitian.gan
 */
public class MaximumSwap {

    // 用一个辅助数组，统计某个下标到末尾的最大值
    public int maximumSwap(int num) {
        Map<Integer, Integer> indexMap = new HashMap<>();

        String s         = String.valueOf(num);
        int[]  maxToTail = new int[s.length()];
        int    tmpMax    = -1;
        for (int j = s.length() - 1; j >= 0; j--) {
            int curr = s.charAt(j) - '0';
            if (curr > tmpMax) {
                tmpMax = curr;
                indexMap.put(tmpMax, j);
            }

            maxToTail[j] = tmpMax;
        }

        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - '0';
            int max = maxToTail[i];
            if(max > curr){
                int deduct = max - curr;
                double result = num - deduct * Math.pow(10, s.length() - 1 - indexMap.get(max)) + deduct * Math.pow(10, s.length() - 1 - i);
                return (int)result;
            }
        }

        return num;
    }

    // 贪婪，虽然没觉得贪婪到哪里去
    public int maximumSwap1(int num) {
        char[] A = Integer.toString(num).toCharArray();
        char[] ans = Arrays.copyOf(A, A.length);
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                char tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                for (int k = 0; k < A.length; k++){
                    if (A[k] != ans[k]){
                        if (A[k] > ans[k]) {
                            ans = Arrays.copyOf(A, A.length);
                        }
                        break;
                    }
                }
                A[j] = A[i];
                A[i] = tmp;
            }
        }
        return Integer.parseInt(new String(ans));
    }

    public static void main(String[] args){
        System.out.println(new MaximumSwap().maximumSwap(9973));
    }

}
