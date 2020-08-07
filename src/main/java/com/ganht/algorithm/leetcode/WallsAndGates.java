package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * 1.-1 - A wall or an obstacle.
 * 2.0 - A gate.
 * 3.INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that
 * the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled
 * with INF.
 *
 * Example:
 *
 * Given the 2D grid:
 *
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * After running your function, the 2D grid should be:
 *
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 * @author haitian.gan
 */
public class WallsAndGates {

    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int         GATE       = 0;
    private static final List<int[]> DIRECTIONS = Arrays.asList(
            new int[] { 1,  0},
            new int[] {-1,  0},
            new int[] { 0,  1},
            new int[] { 0, -1}
    );

    // 也是bfs
    public void wallsAndGates1(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == GATE) {
                    q.add(new int[] { row, col });
                }
            }
        }
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0];
            int col = point[1];
            for (int[] direction : DIRECTIONS) {
                int r = row + direction[0];
                int c = col + direction[1];
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                q.add(new int[] { r, c });
            }
        }
    }

    // 受到另外一道hard题的启发
    // 太死板了，只是死抄。每个gate点都操作会有大量重复的，应该在一起并发进行。
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if(m <= 0){
            return;
        }

        int n = rooms[0].length;
        int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};

        // ini nearest array
        int[][] nearest = new int[m][n];
        for (int i = 0; i < nearest.length; i++) {
            System.arraycopy(rooms[i], 0, nearest[i], 0, nearest[0].length);
        }

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                int val = rooms[i][j];
                if(val != 0){
                    continue;
                }

                boolean[][]  visited = new boolean[m][n];
                Queue<int[]> queue   = new LinkedList<>();
                queue.add(new int[]{i, j});
                int dist = 0;

                while (queue.size() > 0) {
                    dist++;
                    int l = queue.size();
                    for (int k = 0; k < l; k++) {
                        int[] c = queue.poll();
                        for (int[] dir : dirs) {
                            int row = c[0] + dir[0];
                            int col = c[1] + dir[1];
                            if (row < 0 || row >= rooms.length || col < 0 || col >= rooms[0].length || visited[row][col]) {
                                continue;
                            }

                            int v = rooms[row][col];
                            if (v == -1) {
                                nearest[row][col] = -1;
                                continue;
                            } else if (v == 0) {
                                nearest[row][col] = 0;
                            } else {
                                if (nearest[row][col] > dist) {
                                    nearest[row][col] = dist;
                                }
                            }

                            visited[row][col] = true;
                            queue.offer(new int[]{row, col});
                        }
                    }
                }
            }
        }

        for (int i = 0; i < rooms.length; i++) {
            System.arraycopy(nearest[i], 0, rooms[i], 0, rooms[0].length);
        }
    }

    public static void main(String[] args) {
        int[][] input = {
                {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}
        };

        new WallsAndGates().wallsAndGates(input);
    }

}
