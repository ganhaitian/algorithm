package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical,
 * diagonal or anti-diagonal.
     Example:
     Input:
     [[0,1,1,0],
     [0,1,1,0],
     [0,0,0,1]]

    Output: 3
    Hint: The number of elements in the given matrix will not exceed 10,000.
 *
 *
 */
public class LongestLinesOfConsecutiveOneinMax {

    public int longestLine(int[][] M) {
        int[][] offset = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
        Map<String,Integer[]> cache = new HashMap<>();
        int max = 0;
        for(int i = 0;i < M.length;i++){
            for(int j = 0;j < M[0].length;j++){
                if(M[i][j] == 0)
                    continue;

                String c = i + "," + j;
                Integer[] record = cache.getOrDefault(c,new Integer[8]);
                cache.put(c, record);
                Arrays.fill(record,1);

                if(max < 1)
                    max = 1;

                for(int k = 0;k < 8;k++){
                    int tmp1 = i + offset[k][0];
                    int tmp2 = j + offset[k][1];

                    if(tmp1 >= 0 && tmp1 < M.length){
                        if(tmp2 >= 0 && tmp2 < M[0].length){
                            Integer[] ajacent = cache.get(tmp1 + "," + tmp2);
                            if(ajacent != null && ajacent[k] != null && M[i][j] == 1){
                                record[k] = ajacent[k] + 1;
                                if(record[k] > max)
                                    max = record[k];
                            }
                        }
                    }
                }
            }
        }

        return max;
    }

    public static void main(String[] args){
        int[][] test = {{0,1,1,0},{0,1,1,0},{0,0,0,1}};
        new LongestLinesOfConsecutiveOneinMax().longestLine(test);
    }
}
