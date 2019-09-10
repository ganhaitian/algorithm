package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 * @author haitian.gan
 */
public class MinimumWindowSubstring {

    // 海老师(本人)威武
    public String minWindow(String s, String t) {

        if(s == null || s.length() <= 0){
            return "";
        }

        if(t == null || t.length() <= 0){
            return "";
        }

        //Set<String> letters = new HashSet<>();
        Map<String, Integer> letterMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            letterMap.compute(String.valueOf(c), (k, v) -> v == null ? 1 : v + 1);
        }

        int                  min    = Integer.MAX_VALUE;
        String               window = null;
        Map<String, Integer> tmp    = new HashMap<>();
        int                  i      = 0;
        int                  j      = 0;
        String               iStr   = String.valueOf(s.charAt(i));
        String               jStr   = String.valueOf(s.charAt(j));

        if (letterMap.containsKey(iStr)) {
            tmp.put(iStr, 1);
        }

        while (true) {
            if (largerThan(tmp, letterMap)) {
                int size = j - i + 1;
                if (size < min) {
                    min = size;
                    window = s.substring(i, j + 1);
                }

                i++;
                tmp.compute(iStr, (k, v) -> {
                    if (v == null) {
                        return null;
                    }

                    v = v - 1;
                    if (v <= 0) {
                        return null;
                    } else {
                        return v;
                    }
                });

                if (i >= s.length()) {
                    break;
                }

                iStr = String.valueOf(s.charAt(i));
                // 这里面不用再记录一遍
                /*if (letters.contains(iStr)) {
                    tmp.compute(iStr, (k,v) -> v == null ? 1 : v + 1);
                }*/
            } else {
                j++;
                if (j >= s.length()) {
                    break;
                }

                jStr = String.valueOf(s.charAt(j));
                if (letterMap.containsKey(jStr)) {
                    tmp.compute(jStr, (k, v) -> v == null ? 1 : v + 1);
                }
            }
        }

        if (min >= Integer.MAX_VALUE) {
            return "";
        }

        return window;
    }

    private boolean largerThan(Map<String, Integer> m1, Map<String, Integer> m2) {
        if (m1.size() < m2.size()) {
            return false;
        }

        for (Map.Entry<String, Integer> entry1 : m1.entrySet()) {
            if(entry1.getValue() < m2.get(entry1.getKey())){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String window = new MinimumWindowSubstring().minWindow("a", "aa");
        System.out.println(window);
    }

}
