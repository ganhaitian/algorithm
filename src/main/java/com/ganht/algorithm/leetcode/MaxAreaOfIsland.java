package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally
 * (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50.
 * @author haitian.gan
 */
public class MaxAreaOfIsland {

    // 唉，什么狗屁并差集，还是用dfs
    public int maxAreaOfIsland1(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        // 四个方向
        int[][] dirs = {{0,-1},{0,1},{1,0},{-1,0}};
        UnionFindSet findSet = new UnionFindSet(m * n);

        int maxArea = 0;
        Map<Integer, Integer> rootMap = new TreeMap<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                visited[i][j] = true;

                // 初始化一下
                if(grid[i][j] == 1){
                    int _r = findSet.findRoot(index);
                    if(_r == index){
                        rootMap.put(index, 1);
                    }
                }

                for (int[] dir : dirs) {
                    int r = i + dir[0];
                    int c = j + dir[1];

                    if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c]) {
                        continue;
                    }

                    if (grid[r][c] == 1 && grid[i][j] == 1) {
                        int idx = r * n + c;
                        findSet.union(idx, index);
                    }
                }

                if(grid[i][j] == 1){
                    int newArea = rootMap.compute(findSet.findRoot(index), (k, v) -> {
                        if (v == null) {
                            return 1;
                        } else {
                            return v + 1;
                        }
                    });

                    if(newArea > maxArea){
                        maxArea = newArea;
                    }
                }
            }
        }

        return maxArea;
    }


    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 记录访问过的
        boolean[][] visited = new boolean[m][n];
        // 四个方向
        int[][] dirs = {{0,-1},{0,1},{1,0},{-1,0}};
        UnionFindSet findSet = new UnionFindSet(m * n);

        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        // 记录结果的
        int maxArea = 0;
        Map<Integer, Integer> rootMap = new HashMap<>();
        if(grid[0][0] == 1){
            rootMap.put(0, 1);
        }

        while (queue.size() > 0) {
            int[] pos   = queue.poll();
            int   row   = pos[0];
            int   col   = pos[1];
            int   index = row * col + col;

            for (int[] dir : dirs) {
                int r = row + dir[0];
                int c = col + dir[1];

                if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c]) {
                    continue;
                }

                visited[r][c] = true;
                queue.offer(new int[]{r,c});

                if(grid[r][c] == 1){
                    int idx = c * r + c;
                    if(grid[row][col] == 1){
                        findSet.union(idx, index);
                    }

                    int root = findSet.findRoot(idx);
                    int newArea = rootMap.compute(root, (k,v) -> {
                        if(v == null){
                            return 1;
                        }else{
                            return v + 1;
                        }
                    });

                    if(newArea > maxArea){
                        maxArea = newArea;
                    }
                }
            }
        }

        return maxArea;
    }

    // 并查集的应用
    public static class UnionFindSet {
        private int[] elements;
        private int[] heights;

        public UnionFindSet(int n) {
            elements = new int[n];
            heights  = new int[n];
            Arrays.fill(elements, -1);
            Arrays.fill(heights, 1);
        }

        public int findRoot(int i) {
            while (elements[i] != -1) {
                i = elements[i];
            }
            return i;
        }

        public void union(int x, int y) {
            int xRoot = findRoot(x);
            int yRoot = findRoot(y);
            if (xRoot != yRoot) {
                if (heights[xRoot] > heights[yRoot])
                    elements[yRoot] = xRoot;
                else if (heights[xRoot] < heights[yRoot])
                    elements[xRoot] = yRoot;
                else {
                    elements[xRoot] = yRoot;
                    heights[yRoot]++;
                }
            }
        }

    }

    public static void main(String[] args){
        int[][] nums =  {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };

        System.out.println(new MaxAreaOfIsland().maxAreaOfIsland1(nums));
    }

}
