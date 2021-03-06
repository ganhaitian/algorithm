package com.ganht.algorithm.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits
 * existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to
 * return -1.
 *
 * Example 1:
 *
 * Input: 12
 * Output: 21
 *
 *
 * Example 2:
 *
 * Input: 21
 * Output: -1
 */
public class NextGreaterElementIII {

    public int nextGreaterElement(int n) {
        String nSt = String.valueOf(n);
        if(nSt.length() <= 1){
            return -1;
        }

        var map = new TreeMap<Integer, Integer>();
        map.put(nSt.charAt(nSt.length() - 1) - '0', nSt.length() - 1);

        for (int i = nSt.length() - 2; i >= 0; i--) {
            char c   = nSt.charAt(i);
            int  num = c - '0';
            map.put(num, i);

            Map.Entry<Integer, Integer> higherPos = map.higherEntry(num);
            if (higherPos != null) {
                char[] chars = nSt.toCharArray();
                chars[i]                    = chars[higherPos.getValue()];
                chars[higherPos.getValue()] = c;
                return Integer.parseInt(new String(chars));
            }
        }

        return -1;
    }

    // leetcode上面的标准答案，和我的思路就差一点点
    public class Solution {
        public int nextGreaterElement(int n) {
            char[] a = ("" + n).toCharArray();
            int i = a.length - 2;
            while (i >= 0 && a[i + 1] <= a[i]) {
                i--;
            }
            if (i < 0)
                return -1;
            int j = a.length - 1;
            while (j >= 0 && a[j] <= a[i]) {
                j--;
            }
            swap(a, i, j);
            reverse(a, i + 1);
            try {
                return Integer.parseInt(new String(a));
            } catch (Exception e) {
                return -1;
            }
        }
        private void reverse(char[] a, int start) {
            int i = start, j = a.length - 1;
            while (i < j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }
        private void swap(char[] a, int i, int j) {
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static void main(String[] args){
        System.out.println(new NextGreaterElementIII().nextGreaterElement(794621));
    }

}
