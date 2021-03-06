package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with
 * two numbers from 1 to 6 - one on each half of the tile.)
 * <p>
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * <p>
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * <p>
 * If it cannot be done, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the
 * second figure.
 * Example 2:
 * <p>
 * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 * Output: -1
 * Explanation:
 * In this case, it is not possible to rotate the dominoes to make one row of values equal.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A[i], B[i] <= 6
 * 2 <= A.length == B.length <= 20000
 *
 * @author haitian.gan
 */
public class MinimumDominoRotationsForEuqalRow {

    public int minDominoRotations(int[] A, int[] B) {
        Map<String, Integer> rotateNumMap = new HashMap<>();
        int rotateANum = 0;
        int rotateBNum = 0;
        int lastA = 0;
        int lastB = 0;
        for (int i = 0; i < A.length; i++) {
            if(A[i] == lastB){
                rotateANum ++;
            }else if(A[i] != lastA){
                return -1;
            }

            if(B[i] == lastA){
                rotateBNum ++;
            }else if(B[i] != lastB){
                return -1;
            }

            lastA = A[i];
            lastB = B[i];
        }

        return 0;
    }

}
