package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word
 * with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 * Follow up:
 * Try to solve it in O(n log k) time and O(n) extra space.
 * @author haitian.gan
 */
public class TopKFrequentWords {

    // 傻了吧唧的，还借助什么TreeMap，直接按value排序呗
    // 唉，其实用堆是更优雅的
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer>          numMap      = new HashMap<>();
        TreeMap<Integer, Set<String>> frequentMap = new TreeMap<>();

        int newNum, oldNum = 0;
        for (String word : words) {
            newNum = numMap.compute(word, (key, v) -> v == null ? 1 : v + 1);
            oldNum = newNum - 1;

            if (oldNum != 0) {
                frequentMap.computeIfAbsent(oldNum, key -> new TreeSet<>()).remove(word);
            }

            frequentMap.computeIfAbsent(newNum, key -> new TreeSet<>()).add(word);
        }

        List<String> result   = new ArrayList<>();
        int          frequent = frequentMap.lastEntry().getKey();
        while (frequent > 0) {
            for (String word : frequentMap.get(frequent)) {
                result.add(word);
                if (result.size() >= k) {
                    return result;
                }
            }

            frequent--;
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        String[]     inputWords = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int          k          = 4;
        List<String> result     = new TopKFrequentWords().topKFrequent(inputWords, k);
        System.out.println(result);
    }

}
