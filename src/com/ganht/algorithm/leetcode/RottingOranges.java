package com.ganht.algorithm.leetcode;

import java.util.LinkedList;

/**
 * In a given grid, each cell can have one of three values:
 *
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return
 * -1 instead.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 *
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens
 * 4-directionally.
 * Example 3:
 *
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 *
 * Note:
 *
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] is only 0, 1, or 2.
 * @author haitian.gan
 */
public class RottingOranges {

    private int[][] dirs = {{0,-1},{0,1},{-1,0},{1,0}};

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        var rottenQueue = new LinkedList<int[]>();
        int freshNum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int val = grid[i][j];
                if (val == 1) {
                    freshNum++;
                } else if (val == 2) {
                    rottenQueue.offer(new int[]{i, j});
                }
            }
        }

        int minute = 0;
        while (rottenQueue.size() > 0) {
            int size = rottenQueue.size();
            boolean change = false;
            for (int i = 0; i < size; i++) {
                int[] pos = rottenQueue.poll();
                if(pos == null){
                    continue;
                }

                for (int[] dir : dirs) {
                    int r = pos[0] + dir[0];
                    int c = pos[1] + dir[1];
                    if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] != 1) {
                        continue;
                    }

                    grid[r][c] = 2;
                    freshNum --;
                    rottenQueue.offer(new int[]{r, c});
                    change = true;
                }
            }

            if(change)
                minute++;
        }

        if(freshNum > 0){
            return -1;
        }

        return minute;
    }

    public static void main(String[] args) {
        int[][] input = {{0, 2}};
        System.out.println(new RottingOranges().orangesRotting(input));
    }

}
