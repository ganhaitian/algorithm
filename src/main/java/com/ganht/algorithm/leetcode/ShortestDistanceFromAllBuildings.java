package com.ganht.algorithm.leetcode;

import java.util.*;
import java.util.function.Function;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can
 * only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * Example:
 *
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * Output: 7
 *
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
 *              the point (1,2) is an ideal empty land to build a house, as the total
 *              travel distance of 3+3+1=7 is minimal. So return 7.
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 * @author haitian.gan
 */
public class ShortestDistanceFromAllBuildings {

    private Map<String, Integer> cache = new HashMap<>();

    // dfs
    public int shortestDistance(int[][] grid) {

        return -1;
    }

    // 他妈的bfs，挺巧妙的
    // 有一个小巧思就是，要反着找，不是正着求0到每个1的距离是多少，而是反找1到每个0的距离和
    public int shortestDistance1(int[][] g) {
        int m = g.length, n = g[0].length, numBd = 0;
        int[][] sum = new int[m][n], cnt = new int[m][n];
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; ++j) {
                if (g[i][j] != 1) continue;
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{i, j});
                numBd++;
                int dist = 0;
                boolean[][] vs = new boolean[m][n];
                vs[i][j] = true;
                while (!q.isEmpty()) {
                    dist++;
                    int l = q.size();
                    for (int k = 0; k < l; k++) {
                        int[] cur = q.poll();
                        for (int[] d : dir) {
                            int r = cur[0] + d[0], c = cur[1] + d[1];
                            if (r < 0 || r >= m || c < 0 || c >= n || g[r][c] != 0 || vs[r][c]) continue;
                            vs[r][c] = true;
                            sum[r][c] += dist;
                            cnt[r][c]++;
                            q.offer(new int[]{r, c});
                        }
                    }
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; ++j) {
                if (g[i][j] == 0 && cnt[i][j] == numBd){
                    res = Math.min(res, sum[i][j]);
                }
            }
        }

        return res < Integer.MAX_VALUE ? res : -1;
    }

   /* private boolean findHouse(int[][] grid, int[] pos, Set<String> visited) {
        int    row    = pos[0], col = pos[1];
        String posKey = row + "," + col;
        int    land   = grid[row][col];
        int    newRow = row;
        int    newCol = col;

        if (land == 1) {
            visited.add(posKey);
            return true;
        } else if (land == 2) {
            return false;
        } else {
            if (row > 0) {
                newRow = row - 1;
            }

            if (row < grid.length - 1) {
                newRow = row + 1;
            }

            if(col > 0){
                newCol = col - 1;
            }

            if(col < grid[0].length){
                newCol = col + 1;
            }

            Function<int[], Boolean> findFunc = newPos -> {
                if (visited.contains(newPos[0] + "," + newPos[1])) {
                    return false;
                }

                Set<String> newVisited = new HashSet<>(visited);
                newVisited.add(posKey);
                boolean result = findHouse(grid, new int[]{newPos[0], newPos[1]}, newVisited);

            };


        }
    }*/

}
