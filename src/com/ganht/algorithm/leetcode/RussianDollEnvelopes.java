package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into
 * another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Note:
 * Rotation is not allowed.
 *
 * Example:
 *
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * @author haitian.gan
 */
public class RussianDollEnvelopes {

    private static class Envelope{
        int width;
        int height;
        int maxNum;
        List<Envelope> nextList;
        List<Envelope> preList;
    }

    public int maxEnvelopes(int[][] envelopes) {
        List<int[]> envelopeList = new ArrayList<>();
        for (int i = 0; i < envelopes.length; i++) {
            envelopeList.add(envelopes[i]);
        }

        Collections.sort(envelopeList, (a,b) -> {
            System.out.println(Arrays.toString(a) + "," + Arrays.toString(b));
            return Integer.compare(a[0],b[0]);
        });

        return 0;
    }

    public static void main(String[] args){
        int[][] input = {{5,4},{6,4},{6,7},{2,3}};
        new RussianDollEnvelopes().maxEnvelopes(input);
    }

}
