package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 * @author haitian.gan
 */
public class WordLadder {

    private Map<String, Integer> transformCache = new HashMap<>();

    private static class Word implements Comparable<Word>{
        String word;
        private List<Word> transformers = new ArrayList<>();

        Word(String word) {
            this.word = word;
        }

        public void addTransformation(Word word) {
            this.transformers.add(word);
            word.transformers.add(this);
        }

        public boolean equals(Object o) {
            Word w = (Word) o;
            return this.word.equals(w.word);
        }

        public String toString(){
            return word;
        }

        public int hashCode(){
            return word.hashCode();
        }

        @Override
        public int compareTo(Word o) {
            return this.word.compareTo(o.word);
        }
    }

    // 用广度优先搜索，用了种非常巧妙的办法
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        List<Word>        startingPoint = new ArrayList<>();
        Map<String, Word> wordCache     = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            String word1 = wordList.get(i);
            Word   w1    = wordCache.computeIfAbsent(word1, k -> new Word(word1));

            if (canTransform(word1, beginWord) || beginWord.equals(word1)) {
                startingPoint.add(w1);
            }

            for (int j = i + 1; j < wordList.size(); j++) {
                String word2 = wordList.get(j);
                Word   w2    = wordCache.computeIfAbsent(word2, k -> new Word(word2));

                if (canTransform(word1, word2)) {
                    w1.addTransformation(w2);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (Word start : startingPoint) {
            Set<Word> history = new TreeSet<>();
            history.add(start);

            int result = transform(start, endWord, history);
            if (result == 0) {
                continue;
            }

            if (!beginWord.equals(start.word)) {
                result++;
            }

            if (result < min) {
                min = result;
            }
        }

        if(min == Integer.MAX_VALUE){
            return 0;
        }

        return min;
    }

    public boolean canTransform(String word1,String word2){
        int diff = 0;
        for (int k = 0; k < word1.length(); k++) {
            if (word1.charAt(k) != word2.charAt(k)) {
                diff++;
            }
        }

        return diff == 1;
    }

    private int transform(Word w, String target, Set<Word> history) {
        String cacheKey = w + ":" + history.toString();
        Integer cacheMin = transformCache.get(cacheKey);
        if(cacheMin != null){
            return cacheMin;
        }

        if(w.word.equals(target)){
            transformCache.put(cacheKey, 1);
            return 1;
        }

        int min = Integer.MAX_VALUE;
        for (Word t : w.transformers) {
            if (history.contains(t)) {
                continue;
            }

            if(t.word.equals(target)){
                return 2;
            }

            Set<Word> newHistory = new TreeSet<>(history);
            newHistory.add(t);

            int num = transform(t, target, newHistory);
            if(num == 0){
                continue;
            }else{
                num += 1;
            }

            if(num < min){
                min = num;
            }
        }

        if(min == Integer.MAX_VALUE){
            return 0;
        }

        transformCache.put(cacheKey, min);
        return min;
    }

    public static void main(String[] args){
        //List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        //List<String> wordList = Arrays.asList("a","b","c");
        List<String> wordList = Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye");
        int result = new WordLadder().ladderLength("qa","sq", wordList);
        System.out.println(result);
    }

}
