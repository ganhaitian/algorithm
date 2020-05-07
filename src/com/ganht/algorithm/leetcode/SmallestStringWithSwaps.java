package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2
 * indices(0-indexed) of the string.
 * <p>
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 * <p>
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * Example 2:
 * <p>
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * Example 3:
 * <p>
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 *
 * @author haitian.gan
 */
public class SmallestStringWithSwaps {

    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFindSet union = new UnionFindSet(s.length());
        for (List<Integer> list : pairs) {
            int a = list.get(0);
            int b = list.get(1);
            union.union(a, b);
        }
        char[]                                     arr = s.toCharArray();
        HashMap<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int key = union.findRoot(i);
            if (map.containsKey(key)) {
                PriorityQueue<Character> q = map.get(key);
                q.add(arr[i]);
            } else {
                PriorityQueue<Character> q = new PriorityQueue<>();
                q.add(arr[i]);
                map.put(key, q);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int key = union.findRoot(i);
            if (key != -1) {
                arr[i] = map.get(key).poll();
            }
        }

        return new String(arr);
    }

    public static void main(String[] args){
        String s = "zdcyxbwa";
        List<List<Integer>> pairs = new ArrayList<>();
        int[][] rawData = {{0,3},{4,6},{3,4},{1,7},{2,5},{5,7}};
        for (int[] rawDatum : rawData) {
            List<Integer> pair = new ArrayList<>();
            pair.add(rawDatum[0]);
            pair.add(rawDatum[1]);
            pairs.add(pair);
        }

        System.out.println(smallestStringWithSwaps( s,  pairs));
    }

}

// 并查集的应用
class UnionFindSet {
    private int[] elements;
    private int[] heights;

    public UnionFindSet(int n) {
        elements = new int[n];
        heights  = new int[n];
        Arrays.fill(elements, -1);
        Arrays.fill(heights, 1);
    }

    public int findRoot(int i) {
        while (elements[i] != -1) {
            i = elements[i];
        }
        return i;
    }

    public void union(int x, int y) {
        int xRoot = findRoot(x);
        int yRoot = findRoot(y);
        if (xRoot != yRoot) {
            if (heights[xRoot] > heights[yRoot])
                elements[yRoot] = xRoot;
            else if (heights[xRoot] < heights[yRoot])
                elements[xRoot] = yRoot;
            else {
                elements[xRoot] = yRoot;
                heights[yRoot]++;
            }
        }
    }

}
