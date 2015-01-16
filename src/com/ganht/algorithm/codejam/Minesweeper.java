package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem
 * <p/>
 * Minesweeper is a computer game that became popular in the 1980s, and is still included
 * in some versions of the Microsoft Windows operating system. This problem has a similar
 * idea, but it does not assume you have played Minesweeper.
 * <p/>
 * In this problem, you are playing a game on a grid of identical cells. The content of each
 * cell is initially hidden. There are M mines hidden in M different cells of the grid. No other
 * cells contain mines. You may click on any cell to reveal it. If the revealed cell contains a
 * mine, then the game is over, and you lose. Otherwise, the revealed cell will contain a digit
 * between 0 and 8, inclusive, which corresponds to the number of neighboring cells that
 * contain mines. Two cells are neighbors if they share a corner or an edge. Additionally, if
 * the revealed cell contains a 0, then all of the neighbors of the revealed cell are
 * automatically revealed as well, recursively. When all the cells that don't contain mines
 * have been revealed, the game ends, and you win.
 * <p/>
 * For example, an initial configuration of the board may look like this ('*' denotes a mine,
 * and 'c' is the first clicked cell):
 * <p/>
 * *..*...**.
 * ....*.....
 * ..c..*....
 * ........*.
 * ..........
 * <p/>
 * There are no mines adjacent to the clicked cell, so when it is revealed, it becomes a 0,
 * and its 8 adjacent cells are revealed as well. This process continues, resulting in the
 * following board:
 * <p/>
 * *..*...**.
 * 1112*.....
 * 00012*....
 * 00001111*.
 * 00000001.
 * <p/>
 * At this point, there are still un-revealed cells that do not contain mines (denoted by '.'
 * characters), so the player has to click again in order to continue the game.
 * <p/>
 * You want to win the game as quickly as possible. You want to find the minimum number
 * of clicks to win the game. Given the size of the board (N x N), output such minimum
 * number of clicks.
 * <p/>
 * Input
 * The first line of the input gives the number of test cases, T. Ttest cases follow. First line
 * of each test case contains one integer N. N lines strings with length N follows containing
 * '*' and '.', denotes the Minesweeper initial board.
 * <p/>
 * Output
 * <p/>
 * For each test case, output one line containing "Case #x: y", where x is the test case
 * number (starting from 1) and y is the minimum number of clicks to win.
 * <p/>
 * Limits
 * <p/>
 * 1 ≤ T ≤ 100.
 * <p/>
 * <p/>
 * <p/>
 * Created by gan on 2015/1/15.
 */
public class Minesweeper extends CodeJamCase {


    @Override
    protected void runCase() {

    }

    public static class Cell {
        private int num;
        private boolean revealed;
        private boolean mine;
        private List<Cell> neighbours = new ArrayList<Cell>();

        private void addNeibour(Cell cell) {
            neighbours.add(cell);
            if (cell.mine)
                this.num++;
        }

        private void reveal() {
            this.revealed = true;
            if (num == 0) {
                for (Cell neibour : neighbours) {
                    if (!neibour.revealed)
                        neibour.reveal();
                }
            }
        }
    }

    public static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean equals(Object o) {
            if (o != null && o instanceof Position) {
                Position target = (Position) o;
                return this.row == target.row && this.col == target.col;
            }
            return false;
        }

        public int hashCode() {
            return ((Integer) row).hashCode() * ((Integer) col);
        }
    }

    public static int calMinNumClick(List<String> mineGrid) {

        Map<Position, Cell> cache = new HashMap<Position, Cell>();

        List<Cell> mineCells = new ArrayList<Cell>();
        for (int row = 0; row < mineGrid.size(); row++) {
            char[] mineCellStrs = mineGrid.get(row).toCharArray();
            for (int i = 0; i < mineCellStrs.length; i++) {

                Position pos = new Position(row, i);
                Cell cell = cache.get(pos);
                if (cell == null) {
                    cell = new Cell();
                    cache.put(pos, cell);
                }

                if (mineCellStrs[i] == '*')
                    cell.mine = true;
                mineCells.add(cell);

                for (int j = row - 1; j <= row + 1; j++) {
                    if (j < 0 || j >= mineGrid.size())
                        continue;
                    for (int k = i - 1; k <= i + 1; k++) {
                        if (k < 0 || k >= mineCellStrs.length)
                            continue;
                        if (j == row && k == i)
                            continue;
                        Position neighbourPos = new Position(j, k);
                        Cell neighbourCell = cache.get(neighbourPos);
                        if (neighbourCell == null) {
                            neighbourCell = new Cell();
                            cache.put(neighbourPos, neighbourCell);
                        }
                        char neighBourCellStr = mineGrid.get(j).toCharArray()[k];
                        if (neighBourCellStr == '*')
                            neighbourCell.mine = true;
                        cell.addNeibour(neighbourCell);
                    }
                }
            }
        }

        int minNum = 0;
        for (Cell mineCell : mineCells) {
            if (!mineCell.mine && !mineCell.revealed && mineCell.num == 0) {
                mineCell.reveal();
                minNum++;
            }
        }

        for (Cell mineCell : mineCells) {
            if (!mineCell.revealed && !mineCell.mine) {
                minNum++;
            }
        }

        return minNum;
    }

    public static void main(String[] args) {

        final List<String> tmpMineLine = new ArrayList<String>();
        final AtomicInteger caseIndex = new AtomicInteger();

        new Minesweeper().parseInput(
            new File("C:\\Users\\gan\\Downloads\\A-large-practice (2).in"),
            new InputCaseLineParser() {
                @Override
                public void parseLine(int lineNumber, String line) {

                    if (lineNumber == 1)
                        return;

                    if (line.indexOf(".") >= 0 || line.indexOf("*") >= 0) {
                        tmpMineLine.add(line);
                    } else {
                        if (lineNumber > 2) {
                            System.out.println(String.format("Case #%d: %d",
                                caseIndex.incrementAndGet(), calMinNumClick(tmpMineLine)));
                        }
                        tmpMineLine.clear();
                    }
                }
            });

        if (tmpMineLine.size() > 0) {
            System.out.println(String.format("Case #%d: %d",
                caseIndex.incrementAndGet(), calMinNumClick(tmpMineLine)));
        }
    }

}
