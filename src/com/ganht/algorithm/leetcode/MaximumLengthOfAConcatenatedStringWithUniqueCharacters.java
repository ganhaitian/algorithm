package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 *
 * Return the maximum possible length of s.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 * Example 2:
 *
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 * Example 3:
 *
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lower case English letters.
 * @author haitian.gan
 */
public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {

    /**
     * 这个bitMask好骚呀
     * class Solution {
     *     int max = 0;
     *     public int maxLength(List<String> arr) {
     *         List<Integer> list = new LinkedList<Integer>();
     *         for(String a: arr){
     *             int curMask = 0;
     *             for(int i = 0; i < a.length(); i++){
     *                 curMask = curMask | (1 << a.charAt(i) -'a');
     *             }
     *             if(Integer.bitCount(curMask) != a.length())
     *                     continue;
     *                 list.add(curMask);
     *         }
     *         helper(list, 0, 0);
     *         return max;
     *     }
     *
     *     public void helper(List<Integer> list, int n, int mask){
     *         int count = Integer.bitCount(mask);
     *         max = Math.max(count, max);
     *         for(int i = n; i < list.size(); i++){
     *             if((mask & list.get(i)) == 0)
     *             {
     *                helper(list, i + 1, mask | list.get(i));
     *             }
     *         }
     *     }
     * }
     * @return
     */

    // 先用dfs解一波
    public int maxLength(List<String> arr) {
        var result = dfs(arr);
        int max    = 0;
        for (String w : result) {
            if (w.length() > max) {
                max = w.length();
            }
        }

        return max;
    }

    private List<String> dfs(List<String> arr) {
        if (arr.size() <= 1) {
            String str = arr.get(0);
            return isUnique(str) ? arr : Collections.emptyList();
        }

        var first         = arr.get(0);
        var isFirstUnique = isUnique(first);
        var subResult = dfs(arr.subList(1, arr.size()));
        if(!isFirstUnique){
            return subResult;
        }

        var result = new ArrayList<>(subResult);
        result.add(first);
        for(String sub : subResult){
            String tmp = sub + first;
            if(isUnique(tmp)){
                result.add(tmp);
            }
        }

        return result;
    }

    private boolean isUnique(String str){
        var exist = new HashSet<Character>();
        for(char c : str.toCharArray()){
            if(!exist.add(c)){
                return false;
            }
        }

        return true;
    }

    private boolean isUnique(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        Arrays.sort(aChars);
        Arrays.sort(bChars);

        int i = 0, j = 0;
        while (i < aChars.length && j < bChars.length) {
            if (aChars[i] < bChars[j]) {
                i++;
            } else if (aChars[i] == bChars[j]) {
                return false;
            } else {
                j++;
            }
        }

        return true;
    }

    public static void main(String[] args){
        String[] input = {"abc","def","bp","dq","eg","fh"};
        new MaximumLengthOfAConcatenatedStringWithUniqueCharacters().maxLength(Arrays.asList(input));
    }

}
