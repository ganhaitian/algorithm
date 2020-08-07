package com.ganht.algorithm.leetcode;

/**
 *
 *   Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
     The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
     Note that you can only put the bomb at an empty cell.

     Example:
     For the given grid

     0 E 0 0
     E 0 W E
     0 E 0 0

     return 3. (Placing a bomb at (1,1) kills 3 enemies)
     Credits:
     Special thanks to @memoryless for adding this problem and creating all test cases.
 *
 */
public class BombEnemy {

    public int maxKilledEnemies(char[][] grid) {
        if (grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int rowKill = 0;
        int[] colKill = new int[cols];

        int res = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                // horizontal
                if (j == 0 || grid[i][j-1] == 'W') {
                    rowKill = 0;
                    for (int t = j; t < cols && grid[i][t] != 'W'; t++) {
                        if (grid[i][t] == 'E') rowKill ++;
                    }
                }

                // vertical
                if (i == 0 || grid[i-1][j] == 'W') {
                    colKill[j] = 0;
                    for (int t = i; t < rows && grid[t][j] != 'W'; t++) {
                        if (grid[t][j] == 'E') colKill[j] ++;
                    }
                }

                int tempTotal = rowKill + colKill[j];
                if (grid[i][j] == '0') {
                    res = Math.max(res, tempTotal);
                }
            }
        }

        return res;
    }

}
