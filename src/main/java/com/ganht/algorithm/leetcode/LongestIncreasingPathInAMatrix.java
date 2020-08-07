package com.ganht.algorithm.leetcode;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move
 * outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 *
 * Input: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 * Input: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * @author haitian.gan
 */
public class LongestIncreasingPathInAMatrix {

    private int[][] maxMatrix;
    private int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if(m <= 0){
            return 0;
        }

        int n = matrix[0].length;
        this.maxMatrix = new int[m][n];

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = doTraverse(new int[]{i,j}, matrix);
                if(tmp > max){
                    max = tmp;
                }
            }
        }

        return max;
    }

    // DFS
    private int doTraverse(int[] pos, int[][] matrix){
        int row = pos[0];
        int col = pos[1];

        if(maxMatrix[row][col] != 0){
            return maxMatrix[row][col];
        }

        int max = 0;
        for(int[] dir : dirs){
            int r = row + dir[0];
            int c = col + dir[1];

            if(r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length){
                continue;
            }

            int result = maxMatrix[r][c];
            if(matrix[r][c] < matrix[row][col]){
                if(result == 0){
                    result = doTraverse(new int[]{r,c}, matrix);
                }

                if(result > max){
                    max = result;
                }
            }
        }

        max = max + 1;
        maxMatrix[row][col] = max;
        return max;
    }

    public static void main(String[] args) {
        int[][] input = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };
        System.out.println(new LongestIncreasingPathInAMatrix().longestIncreasingPath(input));
    }

}
