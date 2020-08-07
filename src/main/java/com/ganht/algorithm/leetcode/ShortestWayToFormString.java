package com.ganht.algorithm.leetcode;

/**
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).
 *
 * Given two strings source and target, return the minimum number of subsequences of source such that their concatenation
 * equals target. If the task is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: source = "abc", target = "abcbc"
 * Output: 2
 * Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
 * Example 2:
 *
 * Input: source = "abc", target = "acdbc"
 * Output: -1
 * Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d"
 * in target string.
 * Example 3:
 *
 * Input: source = "xyz", target = "xzyxz"
 * Output: 3
 * Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 *
 *
 * Constraints:
 *
 * Both the source and target strings consist of only lowercase English letters from "a"-"z".
 * The lengths of source and target string are between 1 and 1000.
 * @author haitian.gan
 */
public class ShortestWayToFormString {

    public int shortestWay(String source, String target) {
        int sourceIndex = 0;
        int targetIndex = 0;
        int result      = 0;
        while (targetIndex < target.length()) {
            if (sourceIndex >= source.length()) {
                sourceIndex = 0;
                result++;
            }

            if (source.indexOf(target.charAt(targetIndex)) == -1) {
                return -1;
            }

            if (target.charAt(targetIndex) == source.charAt(sourceIndex)) {
                sourceIndex++;
                targetIndex++;
            } else {
                sourceIndex++;
            }
        }

        return result + 1;
    }

    public static void main(String[] args){
        String source = "xyz";
        String target = "xzyxz";
        int result = new ShortestWayToFormString().shortestWay(source, target);
        System.out.print(result);
    }

}
