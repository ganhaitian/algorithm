package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order
 * of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the
 * given words are sorted lexicographicaly in this alien language.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 *
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 *
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical
 * rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other
 * character (More info).
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 * @author haitian.gan
 */
public class VerifyingAnAlienDictionary {

    private Map<Character, Integer> orderMap = new HashMap<>();

    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }

        if(words.length == 1){
            return true;
        }

        for (int i = 1; i < words.length; i++) {
            if(compare(words[i], words[i- 1]) < 0){
                return false;
            }
        }

        return true;
    }

    private int compare(String a, String b) {
        if(a.equals(b)){
            return 0;
        }

        int length = Math.max(a.length(), b.length());
        for (int i = 0; i < length; i++) {
            int aOrder = i >= a.length() ? -1 : orderMap.get(a.charAt(i));
            int bOrder = i >= b.length() ? -1 : orderMap.get(b.charAt(i));
            if (aOrder < bOrder) {
                return -1;
            } else if (aOrder > bOrder) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args){
        String[] input = {"word","world","row"};
        new VerifyingAnAlienDictionary().isAlienSorted(input, "worldabcefghijkmnpqstuvxyz");
    }

}
