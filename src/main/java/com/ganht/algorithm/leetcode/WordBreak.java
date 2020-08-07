package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 *
 * @author haitian.gan
 */
public class WordBreak {

    private Set<String> wordDictSet;

    /**
     * 这个想法非常简洁，但是超时了
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDictSet == null) {
            wordDictSet = new HashSet<>(wordDict);
        }

        for (int i = 1; i <= s.length(); i++) {
            String tryPart = s.substring(0, i);
            if (wordDictSet.contains(tryPart)) {
                String newS = s.substring(i);
                if ("".equals(newS) || wordBreak(newS, wordDict)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        return subWordBreak(s, 0, new HashSet<>(wordDict), new Boolean[s.length()]);
    }

    private boolean subWordBreak(String s, int start, Set<String> wordDictSet, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }

        if (memo[start] != null) {
            return memo[start];
        }

        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDictSet.contains(s.substring(start, end)) && subWordBreak(s, end, wordDictSet, memo)) {
                return memo[start] = true;
            }
        }

        return memo[start] = false;
    }

    public static void main(String[] args){
        /*List<String> dict = Arrays.asList("leet","code");
        boolean result = new WordBreak().wordBreak("leetcode", dict);*/

        /*List<String> dict = Arrays.asList("apple", "pen");
        boolean result = new WordBreak().wordBreak("applepenapple", dict);*/

        List<String> dict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        boolean result = new WordBreak().wordBreak("catsandog", dict);
        System.out.println("result:" + result);
    }

}
