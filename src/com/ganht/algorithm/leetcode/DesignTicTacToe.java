package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 *
 * You may assume the following rules:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves is allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Example:
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 *
 * TicTacToe toe = new TicTacToe(3);
 *
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 *
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 * @author haitian.gan
 */
public class DesignTicTacToe {

    private Map<String, Integer> cache = new HashMap<>();
    private int[][]              cells;
    private int                  N;

    /**
     * Initialize your data structure here.
     */
    public DesignTicTacToe(int n) {
        cells = new int[n][n];
        this.N = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        BiFunction<String, Integer, Integer> accuFunction = (k, v) -> v == null ? 1 : v + 1;
        cells[row][col] = player;
        int result = cache.compute("row:" + row + ":player:" + player, accuFunction);
        if (result >= N) {
            return player;
        }

        result = cache.compute("col:" + col + ":player:" + player, accuFunction);
        if (result >= N) {
            return player;
        }

        if (row == col) {
            result = cache.compute("dia1:player:" + player, accuFunction);
            if (result >= N) {
                return player;
            }
        }

        if ((N - 1) - row == col) {
            result = cache.compute("dia2:player:" + player, accuFunction);
            if (result >= N) {
                return player;
            }
        }

        return 0;
    }

    public static void main(String[] args){
        DesignTicTacToe ticTacToe = new DesignTicTacToe(3);
        ticTacToe.move(0,0,1);
        ticTacToe.move(0,2,2);
        ticTacToe.move(2,2,1);
        ticTacToe.move(1,1,2);
        ticTacToe.move(2,0,1);
        ticTacToe.move(1,0,2);
        ticTacToe.move(2,1,1);

    }

}
