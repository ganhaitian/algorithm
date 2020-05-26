package com.ganht.algorithm.leetcode;

import java.util.*;
import java.util.function.Function;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You
 * receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this
 * new language. Derive the order of letters in this language.
 *
 * Example 1:
 *
 * Input:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 *
 * Output: "wertf"
 * Example 2:
 *
 * Input:
 * [
 *   "z",
 *   "x"
 * ]
 *
 * Output: "zx"
 * Example 3:
 *
 * Input:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 *
 * Output: ""
 *
 * Explanation: The order is invalid, so return "".
 * Note:
 *
 * You may assume all letters are in lowercase.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 * @author haitian.gan
 */
public class AlienDictionary {


    // 想简单了，不止是链表那么简单，原来这是个图呀，因为有多种可能
    public String alienOrder1(String[] words) {

        // Step 0: Create data structures and find all unique letters.
        Map<Character, List<Character>> adjList = new HashMap<>();
        Map<Character, Integer>         counts  = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                counts.put(c, 0);
                adjList.put(c, new ArrayList<>());
            }
        }

        // Step 1: Find all edges.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // Check that word2 is not a prefix of word1.
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }

        // Step 2: Breadth-first search.
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character c : counts.keySet()) {
            if (counts.get(c).equals(0)) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.remove();
            sb.append(c);
            for (Character next : adjList.get(c)) {
                counts.put(next, counts.get(next) - 1);
                if (counts.get(next).equals(0)) {
                    queue.add(next);
                }
            }
        }

        if (sb.length() < counts.size()) {
            return "";
        }
        return sb.toString();
    }

    static class Word{
        char c;
        Word next;
        Word pre;

        Word(char c){
            this.c = c;
        }
    }

    public String alienOrder(String[] words) {
        if(words == null || words.length <= 0){
            return "";
        }

        var lastSet = new HashSet<Character>();
        // word的初始化function
        Function<Character, Word> initWordFunc = k -> {
            lastSet.add(k);
            return new Word(k);
        };

        var wordMap = new HashMap<Character, Word>();
        for (int i = 0; i < words.length - 1; i++) {
            String currStr = words[i];
            String nextStr = words[i + 1];
            boolean stop = false;
            for (int j = 0; j < Math.min(currStr.length(), nextStr.length()); j++) {
                char c1 = currStr.charAt(j);
                char c2 = nextStr.charAt(j);
                Word w1 = wordMap.computeIfAbsent(c1, initWordFunc);
                Word w2 = wordMap.computeIfAbsent(c2, initWordFunc);
                if (c1 != c2 && !stop) {
                    // 如果往前倒w2的前继结点，发现
                    Word curr = w1.pre;
                    while(curr != null){
                        if(curr.c == w2.c){
                            return "";
                        }
                        curr = curr.pre;
                    }

                    w1.next = w2;
                    w2.pre  = w1;
                    lastSet.remove(w1.c);
                    stop = true;
                }
            }
        }

        StringBuilder orderSB = new StringBuilder();
        StringBuilder ext = new StringBuilder();
        for(Iterator<Character> iter = lastSet.iterator();iter.hasNext();){
            Word w = wordMap.get(iter.next());
            if(w.pre == null){
                ext.append(w.c);
            }else{
                while(w != null){
                    orderSB.insert(0, w.c);
                    w = w.pre;
                }
            }
        }

        return orderSB.toString() + ext.toString();
    }

    public static void main(String[] args) {
        String[] input = {"zy", "zx"};
        new AlienDictionary().alienOrder(input);
    }

}
