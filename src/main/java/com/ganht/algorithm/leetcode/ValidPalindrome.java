package com.ganht.algorithm.leetcode;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
 * @author ganha
 *
 */
public class ValidPalindrome {

	public boolean isPalindrome(String s) {
		 s = s.toLowerCase();
		 int i = 0,j = s.length() - 1;
		while (i < j) {
			char left = s.charAt(i);
			char right = s.charAt(j);
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				i++;
			}else if(!Character.isLetterOrDigit(s.charAt(j))) {
				j--;
			}else if(left != right) {
				return false;
			}else {
				i++;
				j--;
			}
		}
		 
		 return true;
	 }
	 
}
