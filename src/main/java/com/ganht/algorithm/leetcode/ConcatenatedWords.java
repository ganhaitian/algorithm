package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 *
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Note:
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 * @author haitian.gan
 */
public class ConcatenatedWords {

    /**
     * 优雅的动态规划解法
     * public class Solution {
     *     public static List<String> findAllConcatenatedWordsInADict(String[] words) {
     *         List<String> result = new ArrayList<>();
     *         Set<String> preWords = new HashSet<>();
     *         Arrays.sort(words, new Comparator<String>() {
     *             public int compare (String s1, String s2) {
     *                 return s1.length() - s2.length();
     *             }
     *         });
     *
     *         for (int i = 0; i < words.length; i++) {
     *             if (canForm(words[i], preWords)) {
     *                 result.add(words[i]);
     *             }
     *             preWords.add(words[i]);
     *         }
     *
     *         return result;
     *     }
     *
     *     private static boolean canForm(String word, Set<String> dict) {
     *         if (dict.isEmpty()) return false;
     * 	boolean[] dp = new boolean[word.length() + 1];
     * 	dp[0] = true;
     * 	for (int i = 1; i <= word.length(); i++) {
     * 	    for (int j = 0; j < i; j++) {
     * 		if (!dp[j]) continue;
     * 		if (dict.contains(word.substring(j, i))) {
     * 		    dp[i] = true;
     * 		    break;
     *                }
     *        }* 	}
     * 	return dp[word.length()];
     *     }
     * }
     */

    private Set<String> wordSet = new HashSet<>();
    private Map<String, Integer> cache = new HashMap<>();

    // 人家管我这个叫做dfs，啧啧
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        wordSet.addAll(Arrays.asList(words));

        var result = new ArrayList<String>();
        for(String word : words){
            int num = countConcatenatedWord(word, 0);
            if(num >= 2){
                result.add(word);
            }
        }

        return result;
    }

    private int countConcatenatedWord(String word, int startIndex) {
        if (startIndex >= word.length()) {
            return 0;
        }

        String  actualWord = word.substring(startIndex);
        Integer cacheVal   = cache.get(actualWord);
        if (cacheVal != null) {
            return cacheVal;
        }

        int result = -1;
        for (int i = startIndex + 1; i <= word.length(); i++) {
            String sub = word.substring(startIndex, i);
            if (!wordSet.contains(sub)) {
                continue;
            }

            int tmp = countConcatenatedWord(word, i);
            if(tmp == -1){
                continue;
            }

            tmp = tmp + 1;
            if(tmp > result){
                result = tmp;
            }
        }

        cache.put(actualWord, result);
        return result;
    }

    public static void main(String[] args){
        String[] input = {"a", "b", "ab", "abc"};
        new ConcatenatedWords().findAllConcatenatedWordsInADict(input);
    }

}
