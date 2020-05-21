package com.ganht.algorithm.leetcode;

import java.util.*;
import java.util.function.Function;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 *
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
 * queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 * @author haitian.gan
 */
public class NQueens {

    private List<List<String>>             result      = new ArrayList<>();
    private Function<String, Set<Integer>> initSetFunc = k -> new HashSet<>();

    public List<List<String>> solveNQueens(int n) {
        solveRow(0, new int[n], n, new HashMap<>());
        return result;
    }

    private void solveRow(int row, int[] historyPos, int n, Map<String,Set<Integer>> bannedMap) {
        for (int col = 0; col < n; col++) {
            Set<Integer> colBannedSet = bannedMap.computeIfAbsent("col", initSetFunc);
            if(colBannedSet.contains(col)){
                continue;
            }

            Set<Integer> dia1BannedSet = bannedMap.computeIfAbsent("dia1", initSetFunc);
            if(dia1BannedSet.contains(col - row)){
                continue;
            }

            Set<Integer> dia2BannedSet = bannedMap.computeIfAbsent("dia2", initSetFunc);
            if(dia2BannedSet.contains(row + col)){
                continue;
            }

            historyPos[row] = col;
            if(row < n - 1){
                colBannedSet.add(col);
                dia1BannedSet.add(col - row);
                dia2BannedSet.add(row + col);
                solveRow(row + 1, historyPos, n, bannedMap);

                // 清理现场，回到递归之前
                colBannedSet.remove(col);
                dia1BannedSet.remove(col - row);
                dia2BannedSet.remove(row + col);
                historyPos[row] = 0;
            } else {
                // 成功了!
                List<String> solution = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < n; j++) {
                        if (j == historyPos[i]) {
                            sb.append("Q");
                        } else {
                            sb.append(".");
                        }
                    }

                    solution.add(sb.toString());
                }

                result.add(solution);
            }
        }
    }

    public static void main(String[] args){
        System.out.println(new NQueens().solveNQueens(5));
    }

}
