package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * To some string S, we will perform some replacement operations that replace groups of letters with new ones
 * (not necessarily the same size).
 *
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that
 * if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.
 *
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd"
 * starts at position 2 in the original string S, we will replace it with "ffff".
 *
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as
 * another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original
 * string S[2] = 'c', which doesn't match x[0] = 'e'.
 *
 * All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example,
 * S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 *
 * Example 1:
 *
 * Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * Output: "eeebffff"
 * Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
 * "cd" starts at index 2 in S, so it's replaced by "ffff".
 * Example 2:
 *
 * Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation: "ab" starts at index 0 in S, so it's replaced by "eee".
 * "ec" doesn't starts at index 2 in the original S, so we do nothing.
 * Notes:
 *
 * 0 <= indexes.length = sources.length = targets.length <= 100
 * 0 < indexes[i] < S.length <= 1000
 * All characters in given inputs are lowercase letters.
 * @author haitian.gan
 */
public class FindAndReplaceInString {

    public String findReplaceString_1(String S, int[] indexes, String[] sources, String[] targets) {
        int[]  offsetCache = new int[999];
        String result      = S;
        for (int i = 0; i < indexes.length; i++) {
            int offset = 0;
            for (int j = 0; j < indexes[i]; j++) {
                offset += offsetCache[j];
            }

            int    index  = indexes[i] + offset;
            String src    = sources[i];
            String target = targets[i];

            if (result.indexOf(src, index) == index) {
                result = result.substring(0, index) + result.substring(index).replaceFirst(src, target);
                offsetCache[indexes[i]] = target.length() - src.length();
            }
        }

        return result;
    }

    private static class ReplaceStr implements Comparable<ReplaceStr> {
        int    index;
        String src;
        String target;

        public ReplaceStr(int index, String src, String target) {
            this.index = index;
            this.src = src;
            this.target = target;
        }

        @Override
        public int compareTo(ReplaceStr o) {
            return Integer.compare(this.index, o.index);
        }
    }

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        List<ReplaceStr> replaceStrList = new ArrayList<>();
        for (int i = 0; i < indexes.length; i++) {
            replaceStrList.add(new ReplaceStr(indexes[i], sources[i], targets[i]));
        }

        // 排个序
        Collections.sort(replaceStrList);

        String result = S;
        int    offset = 0;
        for (ReplaceStr replaceStr : replaceStrList) {
            int    index  = replaceStr.index + offset;
            String src    = replaceStr.src;
            String target = replaceStr.target;

            if (result.indexOf(src, index) == index) {
                result = result.substring(0, index) + result.substring(index).replaceFirst(src, target);
                offset += target.length() - src.length();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //System.out.println("acbcdef".indexOf("c",2));
        int[]    indexes = {3,5,1};
        String[] sources = {"kg","ggq","mo"};
        String[] targets = {"s","so","bfr"};
        String   result  = new FindAndReplaceInString().findReplaceString("vmokgggqzp", indexes, sources, targets);
        System.out.println(result);
    }

}
