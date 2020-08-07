package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Let's define a function countUniqueChars(s) that returns the number of unique characters on s, for example if s = "LEETCODE"
 * then "L", "T","C","O","D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
 * <p>
 * On this problem given a string s we need to return the sum of countUniqueChars(t) where t is a substring of s. Notice
 * that some substrings can be repeated so on this case you have to count the repeated ones too.
 * <p>
 * Since the answer can be very large, return the answer modulo 10 ^ 9 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Evey substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 * Example 2:
 * <p>
 * Input: s = "ABA"
 * Output: 8
 * Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
 * Example 3:
 * <p>
 * Input: s = "LEETCODE"
 * Output: 92
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 10^4
 * s contain upper-case English letters only.
 *
 * @author haitian.gan
 */
public class CountUniqueCharactersOfAllSubstringsOfAGivenString {

    Map<Character, List<Integer>> index;
    int[]                         peek;
    int N;

    public int uniqueLetterString1(String S) {
        index = new HashMap<>();
        peek  = new int[26];
        N     = S.length();

        for (int i = 0; i < S.length(); ++i) {
            char c = S.charAt(i);
            index.computeIfAbsent(c, x -> new ArrayList<>()).add(i);
        }

        long cur = 0, ans = 0;
        for (char c: index.keySet()) {
            index.get(c).add(N);
            index.get(c).add(N);
            cur += get(c);
        }

        for (char c: S.toCharArray()) {
            ans += cur;
            long oldv = get(c);
            peek[c - 'A']++;
            cur += get(c) - oldv;
        }
        return (int) ans % 1_000_000_007;
    }

    public long get(char c) {
        List<Integer> indexes = index.get(c);
        int i = peek[c - 'A'];
        return indexes.get(i+1) - indexes.get(i);
    }

    /**
     * 人家这个方法好巧妙呀，还是得用巧劲儿
     * 有的时候正着想不行，就反着想，不是想哪些字串能包含多少个UniqueChar，而是想一个字母能有多少种不同的字串组合可能性
     * class Solution {
     *     public int uniqueLetterString(String S) {
     *         Map<Character, List<Integer>> index = new HashMap();
     *         for (int i = 0; i < S.length(); ++i) {
     *             char c = S.charAt(i);
     *             index.computeIfAbsent(c, x-> new ArrayList<Integer>()).add(i);
     *         }
     *
     *         long ans = 0;
     *         for (List<Integer> A: index.values()) {
     *             for (int i = 0; i < A.size(); ++i) {
     *                 long prev = i > 0 ? A.get(i-1) : -1;
     *                 long next = i < A.size() - 1 ? A.get(i+1) : S.length();
     *                 ans += (A.get(i) - prev) * (next - A.get(i));
     *             }
     *         }
     *
     *         return (int) ans % 1_000_000_007;
     *     }
     * }
     * @param s
     * @return
     */

    // 好像用一个DP就可以了
    // 应该记录的是最后一个重复字母出现的位置
    public int uniqueLetterString(String s) {
        int N          = s.length();
        var dp         = new long[N];
        var lastPosMap = new HashMap<Character, Integer>(); // 记录出现过的字母

        if(s.length() <= 1){
            return 1;
        }

        // 初始化
        dp[0] = 1;
        lastPosMap.put(s.charAt(0), 0);
        for (int i = 1; i < s.length(); i++) {
            var c = s.charAt(i);
            var lastPos = lastPosMap.get(c);
            if(lastPos == null){
                dp[i] = dp[i - 1] + calSumFrom1(i + 1);
            }else{
                dp[i] = dp[i - 1] + calSumFrom1(i - lastPos);
            }

            lastPosMap.put(c, i);
        }

        return (int)(dp[N - 1] % 1000000007);
    }

    // 计算从1加到100的后
    private int calSumFrom1(int n){
        return (int)((1 + n) * n / 2d);
    }

    public static void main(String[] args){
        new CountUniqueCharactersOfAllSubstringsOfAGivenString().uniqueLetterString1("LEETCODE");
    }

}
