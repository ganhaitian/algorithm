package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.BiFunction;

/**
 * Given a string, find the length of the longest substring T that contains at
 * most k distinct characters.
 * 
 * Example 1:
 * 
 * Input: s = "eceba", k = 2 Output: 3 Explanation: T is "ece" which its length
 * is 3. Example 2:
 * 
 * Input: s = "aa", k = 1 Output: 2 Explanation: T is "aa" which its length is
 * 2.
 * 
 * @author ganha
 *
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		int max = 0;
		int start = 0;
		int tmpMax = 0;
		var map = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			map.compute(s.charAt(i),  (key, v) -> v == null ? 1 : v + 1);
			if (map.size() > k) {
				do {
					map.computeIfPresent(s.charAt(start++), (key,v) -> v <= 1 ? null : v - 1);
				} while (map.size() > k);
				
				tmpMax = i - start + 1;
			} else {
				if (++tmpMax > max) {
					max = tmpMax;
				}
			}
		}

		return max;
	}

	public static void main(String[] args) {
		new LongestSubstringWithAtMostKDistinctCharacters().lengthOfLongestSubstringKDistinct("abaccc", 2);
	}

}
