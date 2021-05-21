package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where
 * heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope
 * to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and
 * you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * Example 2:
 *
 *
 *
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better
 * than route [1,3,5,3,5].
 * Example 3:
 *
 *
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 *
 * Constraints:
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 */
public class PathWithMinimumEffort {
    
    public int minimumEffortPath(int[][] heights) {
        int[][] offsets   = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int     maxWidth  = heights[0].length;
        int     maxHeight = heights.length;
    
        // 深度优先搜索
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[]{0, 0});
    
        Stack<Integer> preValStack = new Stack<>();
        Set<String>    visited     = new HashSet<>();
        Integer        preValue    = null;
        int            tmpEffort   = 0;
        int            minEffort   = Integer.MAX_VALUE;
        while (stack.size() > 0) {
            int[] curr = stack.pop();
            int val = heights[curr[0]][curr[1]];
            if (preValue != null) {
                tmpEffort = Math.max(Math.abs(val - preValue), tmpEffort);
                if(tmpEffort > minEffort){
                    continue;
                }
            }
            preValue = val;
            
            
            if(curr[0] == maxHeight - 1 && curr[1] == maxWidth - 1){
                if(tmpEffort < minEffort){
                    minEffort = tmpEffort;
                }
    
                tmpEffort = 0;
                preValue = 0;
                continue;
            }
            
            visited.add(curr[0] + "," + curr[1]);
            for(int[] offset : offsets){
                int[] next = {curr[0] + offset[0], curr[1] + offset[1]};
                if (next[0] >= 0 &&
                        next[0] < maxWidth &&
                        next[1] >= 0 &&
                        next[1] < maxHeight &&
                        !visited.contains(next[0] + "," + next[1])) {
                    stack.push(next);
                }
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        new PathWithMinimumEffort().minimumEffortPath(heights);
    }
    
    class Solution {
        public int minimumEffortPath(int[][] heights) {
            int left = 0;
            int right = 1000000;
            int result = right;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (canReachDestinaton(heights, mid)) {
                    result = Math.min(result, mid);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return result;
        }
        
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        // use bfs to check if we can reach destination with max absolute difference k
        boolean canReachDestinaton(int[][] heights, int k) {
            int row = heights.length;
            int         col     = heights[0].length;
            Deque<Cell> queue   = new ArrayDeque<>();
            boolean[][] visited = new boolean[heights.length][heights[0].length];
            queue.addLast(new Cell(0, 0));
            visited[0][0] = true;
            while (!queue.isEmpty()) {
                Cell curr = queue.removeFirst();
                if(curr.x == row - 1 && curr.y == col - 1) {
                    return true;
                }
                for (int[] direction : directions) {
                    int adjacentX = curr.x + direction[0];
                    int adjacentY = curr.y + direction[1];
                    if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                        int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                        if (currentDifference <= k) {
                            visited[adjacentX][adjacentY] = true;
                            queue.addLast(new Cell(adjacentX, adjacentY));
                        }
                    }
                }
            }
            return false;
        }
        
        boolean isValidCell(int x, int y, int row, int col) {
            return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
        }
    }
    
    class Cell {
        int x;
        int y;
        
        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
