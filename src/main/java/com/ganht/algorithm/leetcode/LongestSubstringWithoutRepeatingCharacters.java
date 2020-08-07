package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * @author haitian.gan
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> posCache = new HashMap<Character, Integer>();
        int                     index    = 0;
        int offset    = 0;
        int maxLength = 0;
        for (char c : s.toCharArray()) {
            Integer lastOccurIndex = posCache.remove(c);
            if (lastOccurIndex != null && lastOccurIndex >= offset) {
                int tmpLength = index - offset;
                if (tmpLength > maxLength) {
                    maxLength = tmpLength;
                }

                offset = lastOccurIndex + 1;
            }

            posCache.put(c, index);
            index++;
        }

        int lastPart = index - offset;
        if (lastPart > maxLength) {
            maxLength = lastPart;
        }

        if(maxLength == 0){
            maxLength = s.length();
        }

        return maxLength;
    }

    public static void main(String[] args){
        int num = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("bbbbb");
        System.out.println(num);
    }

}
