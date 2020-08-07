package com.ganht.algorithm.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * Alice has a hand of cards, given as an array of integers.
 *
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 *
 * Return true if and only if she can.
 *
 *
 *
 * Example 1:
 *
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], W = 4
 * Output: false
 * Explanation: Alice's hand can't be rearranged into groups of 4.
 *
 *
 * Note:
 *
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 * @author haitian.gan
 */
public class HandOfStraights {

    public boolean isNStraightHand(int[] hand, int W) {
        var timesMap = new TreeMap<Integer, Integer>();
        for (int value : hand) {
            timesMap.compute(value, (k, v) -> v == null ? 1 : v + 1);
        }

        while (timesMap.size() > 0) {
            int length = 0;
            var entry  = timesMap.firstEntry();
            while (entry != null && length < W) {
                Integer num = entry.getKey();
                timesMap.computeIfPresent(num, (k, v) -> v - 1 > 0 ? v - 1 : null);

                length++;
                entry = timesMap.higherEntry(num);
                if(length <= W - 1 && (entry == null || entry.getKey() - num > 1)){
                    return false;
                }
            }

            if (length < W) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){

    }

}
