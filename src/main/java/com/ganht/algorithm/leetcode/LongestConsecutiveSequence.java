package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 *
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * @author haitian.gan
 */
public class LongestConsecutiveSequence {

    public static class Range{
        int start;
        int end;
    }

    // 想复杂了
    public int longestConsecutive(int[] nums) {
        Map<Integer, Range> cache = new HashMap<>();
        for (int num : nums) {
            Range leftRange  = cache.get(num - 1);
            Range rightRange = cache.get(num + 1);
            Range newRange   = new Range();

            if (leftRange == null) {
                newRange.start = num;
            } else {
                newRange.start = leftRange.start;
            }

            if (rightRange == null) {
                newRange.end = num;
            } else {
                rightRange.start = newRange.start;
                if (leftRange != null) {
                    leftRange.end = rightRange.end;
                    cache.get(leftRange.start).end = rightRange.end;
                    cache.get(rightRange.end).start = leftRange.start;
                }
            }

            cache.put(num, newRange);
        }

        return 0;
    }


}
