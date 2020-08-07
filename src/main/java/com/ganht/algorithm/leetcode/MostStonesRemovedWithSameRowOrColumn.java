package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
 * <p>
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 * <p>
 * What is the largest possible number of moves we can make?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Example 2:
 * <p>
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Example 3:
 * <p>
 * Input: stones = [[0,0]]
 * Output: 0
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 *
 * @author haitian.gan
 */
public class MostStonesRemovedWithSameRowOrColumn {

    private static class Point implements Comparable<Point>{

        private int[] val;
        private int weight;

        @Override
        public int compareTo(Point o) {
            if(o == null){
                return 1;
            }

            return Integer.compare(this.weight, o.weight);
        }
    }

    public int removeStones(int[][] stones) {
        Map<Integer, Set<Integer>> xMap = new HashMap<>();
        Map<Integer, Set<Integer>> yMap = new HashMap<>();
        for (int[] stone : stones) {
            xMap.computeIfAbsent(stone[0], k -> new HashSet<>()).add(stone[1]);
            yMap.computeIfAbsent(stone[1], k -> new HashSet<>()).add(stone[0]);
        }

        Map<Integer, List<int[]>> stoneMap = new HashMap<>();
        for (int[] stone : stones) {
            int num = 0;
            num += Math.min(xMap.get(stone[0]).size() - 1, 2);
            num += Math.min(yMap.get(stone[1]).size() - 1, 2);


            stoneMap.computeIfAbsent(num, k -> new ArrayList<>()).add(stone);
        }

        int result = 0;
        for (int i = 1; i <= 4; i++) {
            for (int[] stone : stoneMap.getOrDefault(i, new ArrayList<>())) {
                Set<Integer> xVals = xMap.get(stone[0]);
                if (xVals.size() > 1) {
                    result++;

                    xVals.remove(stone[1]);
                    yMap.get(stone[1]).remove(stone[0]);
                    continue;
                }

                Set<Integer> yVlas = yMap.get(stone[1]);
                if (yVlas.size() > 1) {
                    result++;

                    yVlas.remove(stone[0]);
                    xMap.get(stone[0]).remove(stone[1]);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] input  = {{3, 2}, {3, 1}, {4, 4}, {1, 1}, {0, 2}, {4, 0}};
        int     result = new MostStonesRemovedWithSameRowOrColumn().removeStones(input);
        System.out.println(result);
    }

}
