package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero
 * or more conversions.
 *
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 *
 * Return true if and only if you can transform str1 into str2.
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 * Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
 * Example 2:
 *
 * Input: str1 = "leetcode", str2 = "codeleet"
 * Output: false
 * Explanation: There is no way to transform str1 to str2.
 *
 *
 * Note:
 *
 * 1 <= str1.length == str2.length <= 10^4
 * Both str1 and str2 contain only lowercase English letters.
 * @author haitian.gan
 */
public class StringTransformsIntoAnotherString {

    public boolean canConvert(String str1, String str2) {
        var transMap = new HashMap<Character, Character>();
        for (int i = 0; i < str1.length(); i++) {
            char      c1  = str1.charAt(i);
            char      c2  = str2.charAt(i);
            Character toC = transMap.get(c1);
            if (toC == null) {
                transMap.put(c1, c2);
            } else if (toC != c2) {
                return false;
            }
        }

        return new HashSet<Character>(transMap.values()).size() < 26;
    }

    public static void main(String[] args){
        new StringTransformsIntoAnotherString().canConvert("leetcode", "codeleet");
    }

}
