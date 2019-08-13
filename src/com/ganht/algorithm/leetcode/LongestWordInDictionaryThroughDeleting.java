package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some
 * characters of the given string. If there are more than one possible results, return the longest word with the smallest
 * lexicographical order. If there is no possible result, return the empty string.
 * <p>
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * <p>
 * Output:
 * "apple"
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 * <p>
 * Output:
 * "a"
 *
 * @author haitian.gan
 */
public class LongestWordInDictionaryThroughDeleting {

    public String findLongestWord(String s, List<String> d) {
        String result = "";
        for (String dd : d) {
            int i = 0, j = 0;
            while (i < s.length() && j < dd.length()) {
                if (s.charAt(i) == dd.charAt(j)) {
                    i++;
                    j++;
                } else {
                    i++;
                }
            }

            if (j == dd.length()) {
                if (result.equals("")) {
                    result = dd;
                } else if (dd.length() > result.length()) {
                    result = dd;
                } else if (dd.length() == result.length() && dd.compareTo(result) < 0) {
                    result = dd;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        new LongestWordInDictionaryThroughDeleting().findLongestWord(
                "abpcplea",
                Arrays.asList("ale","apple","monkey","plea")
        );
    }

}
