package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * A string S represents a list of words.
 *
 * Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is
 * more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].
 *
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 *
 * Return all words that can be formed in this manner, in lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 * Example 2:
 *
 * Input: "abcd"
 * Output: ["abcd"]
 *
 *
 * Note:
 *
 * 1 <= S.length <= 50
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 * @author haitian.gan
 */
public class BraceExpansion {

    public String[] expand(String S) {
        int braceStart = S.indexOf("{");
        int braceEnd   = S.indexOf("}");

        if (braceStart == -1 && braceEnd == -1) {
            return new String[]{S};
        }

        String[] afterStrs;
        if (braceEnd < S.length() - 1) {
            afterStrs = expand(S.substring(braceEnd + 1));
        } else {
            afterStrs = new String[0];
        }

        List<String> result = new ArrayList<>();
        String       prefix = S.substring(0, braceStart);
        String       option = S.substring(braceStart + 1, braceEnd);
        for (String o : option.split(",")) {
            if (afterStrs.length > 0) {
                for (String afterStr : afterStrs) {
                    result.add(prefix + o + afterStr);
                }
            } else {
                result.add(prefix + String.valueOf(o));
            }
        }

        result.sort(String::compareTo);
        return result.toArray(new String[0]);
    }

    public static void main(String[] args){
        String input = "{a,b}c{d,e}";
        //String input = "abcd";
        String[] b = new BraceExpansion().expand(input);
        System.out.println(b);
    }

}
