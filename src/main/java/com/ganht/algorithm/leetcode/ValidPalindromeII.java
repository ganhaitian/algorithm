package com.ganht.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 * @author ganha
 *
 */
public class ValidPalindromeII {
	
	public boolean validPalindrome(String s) {
		int[] times = new int[26];
		for(int i = 0;i < s.length();i++) {
			times[s.charAt(i) - 'a']++;
		}
		
		int oddNum= 0;
		for(int time : times) {
			if(time % 2 > 0) {
				oddNum ++;
			}
		}
		
		return s.length() % 2 == 0 ? oddNum <= 1 : oddNum <= 2;
    }
	
	
	public boolean validPalindrome1(String s) {
		int i = 0,j = s.length() - 1;
		int delNum = 0;
		while(i < j) {
			if(s.charAt(i) == s.charAt(j)) {
				i++;
				j--;
			}else {
				if(s.charAt(i + 1) == s.charAt(j)) {
					i++;
				}else if(s.charAt(i) == s.charAt(j - 1)) {
					j--;
				}else {
					return false;
				}
				
				delNum++;
				if(delNum > 1) {
					return false;
				}
			}
		}
		
		return true;
    }
	
	// 其实关键是要把两种删除情况都试一下
	public boolean isPalindromeRange(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) return false;
        }
        return true;
    }
    public boolean validPalindrome2(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j = s.length() - 1 - i;
                return (isPalindromeRange(s, i+1, j) ||
                        isPalindromeRange(s, i, j-1));
            }
        }
        return true;
    }
	
	public static void main(String[] args) {
		new ValidPalindromeII().validPalindrome1("abcsbak");
	}

}
