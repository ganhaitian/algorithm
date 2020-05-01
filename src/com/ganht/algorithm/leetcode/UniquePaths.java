package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner
 * of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 *
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 */
public class UniquePaths {

    private int m;
    private int n;
    private int wayNums;

    private Map<Integer, Integer> cache = new HashMap<>();

    public int uniquePaths(int m, int n) {
        this.m = m;
        this.n = n;

        return visit(1, this.n);
    }

    // 动态规划一步搞定
    private int visit1(int x,int y){
        int[][] d = new int[m][n];

        for(int[] arr : d) {
            Arrays.fill(arr, 1);
        }
        for(int col = 1; col < m; ++col) {
            for(int row = 1; row < n; ++row) {
                d[col][row] = d[col - 1][row] + d[col][row - 1];
            }
        }
        return d[m - 1][n - 1];
    }

    private int visit(int x,int y){
        int key = x * 10 + y;
        Integer cacheResult = cache.get(key);
        if(cacheResult != null){
            // wayNums += cacheResult;
            return cacheResult;
        }

        if(y == 1 && x == m){
            //wayNums ++;
            return 1;
        }

        int sum = 0;
        int rightX = x + 1;
        if(rightX <= m){
            sum += visit(rightX, y);
        }

        int downY = y - 1;
        if(y >= 1){
            sum += visit(x, downY);
        }

        // 放入缓存
        //cache.put(key, sum);
        return sum;
    }

    public static void main(String[] args){
        System.out.println(new UniquePaths().uniquePaths(9, 11));
    }

}
