package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board
 * position during the course of a valid tic-tac-toe game.
 *
 * The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.
 *
 * Here are the rules of Tic-Tac-Toe:
 *
 * Players take turns placing characters into empty squares (" ").
 * The first player always places "X" characters, while the second player always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never filled ones.
 * The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * Example 1:
 * Input: board = ["O  ", "   ", "   "]
 * Output: false
 * Explanation: The first player always plays "X".
 *
 * Example 2:
 * Input: board = ["XOX", " X ", "   "]
 * Output: false
 * Explanation: Players take turns making moves.
 *
 * Example 3:
 * Input: board = ["XXX", "   ", "OOO"]
 * Output: false
 *
 * Example 4:
 * Input: board = ["XOX", "O O", "XOX"]
 * Output: true
 * Note:
 *
 * board is a length-3 array of strings, where each string board[i] has length 3.
 * Each board[i][j] is a character in the set {" ", "X", "O"}.
 * @author haitian.gan
 */
public class ValidTicTacToeState {


    /**
     * class Solution {
     *     public boolean validTicTacToe(String[] board) {
     *         int xCount = 0, oCount = 0;
     *         for (String row: board)
     *             for (char c: row.toCharArray()) {
     *                 if (c == 'X') xCount++;
     *                 if (c == 'O') oCount++;
     *             }
     *
     *         if (oCount != xCount && oCount != xCount - 1) return false;
     *         if (win(board, 'X') && oCount != xCount - 1) return false;
     *         if (win(board, 'O') && oCount != xCount) return false;
     *         return true;
     *     }
     *
     *     public boolean win(String[] B, char P) {
     *         // B: board, P: player
     *         for (int i = 0; i < 3; ++i) {
     *             if (P == B[0].charAt(i) && P == B[1].charAt(i) && P == B[2].charAt(i))
     *                 return true;
     *             if (P == B[i].charAt(0) && P == B[i].charAt(1) && P == B[i].charAt(2))
     *                 return true;
     *         }
     *         if (P == B[0].charAt(0) && P == B[1].charAt(1) && P == B[2].charAt(2))
     *             return true;
     *         if (P == B[0].charAt(2) && P == B[1].charAt(1) && P == B[2].charAt(0))
     *             return true;
     *         return false;
     *     }
     * }
     * @param board
     * @return
     */

    // 鸡巴的想复杂了
    public boolean validTicTacToe(String[] board) {
        var xList = new LinkedList<int[]>();
        var oList = new LinkedList<int[]>();
        for (int row = 0; row < board.length; row++) {
            String rowStr = board[row];
            for (int col = 0; col < rowStr.length(); col++) {
                char c   = rowStr.charAt(col);
                var  pos = new int[]{row, col};
                switch (c) {
                    case 'X':
                        xList.add(pos);
                        break;
                    case 'O':
                        oList.add(pos);
                        break;
                    default:
                        break;
                }
            }
        }

        if(xList.size() - oList.size() < 0 || xList.size() - oList.size() > 1){
            return false;
        }

        // 合并
        var cList     = new LinkedList<int[]>();
        var xListSize = xList.size();
        for (int i = 0; i < xListSize; i++) {
            cList.offer(xList.poll());
            cList.offer(oList.poll());
        }

        // 递增func
        BiFunction<String, Integer, Integer> incBiFunc = (k, v) -> v == null ? 1 : v + 1;

        var cache = new HashMap<String, Integer>();
        Function<int[], Boolean> judgeFunc = pos -> {
            char c   = board[pos[0]].charAt(pos[1]);
            int  num = cache.compute(c + "R" + pos[0], incBiFunc);
            if (num >= 3) {
                return true;
            }

            num = cache.compute(c + "C" + pos[1], incBiFunc);
            if (num >= 3) {
                return true;
            }

            // 对角线
            if (pos[0] == pos[1]) {
                num = cache.compute(c + "D1", incBiFunc);

            } else if (pos[0] + pos[1] == 2) {
                num = cache.compute(c + "D2", incBiFunc);
            }

            return num >= 3;
        };

        int space = 9;
        while(cList.size() > 0){
            int[] pos = cList.poll();
            if(pos == null){
                continue;
            }

            boolean win = judgeFunc.apply(pos);
            if(win){
                return cList.size() <= 0;
            }

            space --;
        }

        return true;
    }

    public static void main(String[] args){
        String[] input = {"XOX", "O O", "XOX"};
        new ValidTicTacToeState().validTicTacToe(input);
    }

}
