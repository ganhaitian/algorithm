package com.ganht.algorithm.leetcode;

/**
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
 *
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 *
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 *
 *
 *
 * Example 1:
 *
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * Example 2:
 *
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 *
 *
 * Note:
 *
 * cells.length == 8
 * cells[i] is in {0, 1}
 * 1 <= N <= 10^9
 * @author haitian.gan
 */
public class PrisonCellsAfterNDays {

    // 傻乎乎的一直循环到底，实际上是有周期的
    public int[] prisonAfterNDays(int[] cells, int N) {
        int   cellNum     = cells.length;
        int[] extendCells = new int[cellNum * 2];
        System.arraycopy(cells, 0, extendCells, 0, 8);

        int oldOffset = 0;
        int newOffset = 0;
        for (int i = 1; i <= N; i++) {
            if (i % 2 == 1) {
                oldOffset = 0;
                newOffset = 8;
            } else {
                oldOffset = 8;
                newOffset = 0;
            }

            int oldIndex = 0;
            int newIndex = 0;
            for (int j = 0; j < cellNum; j++) {
                oldIndex = j + oldOffset;
                newIndex = j + newOffset;

                if (oldIndex == 0 || oldIndex == 7 || oldIndex == 8 || oldIndex == 15) {
                    extendCells[newIndex] = 0;
                } else if ((extendCells[oldIndex - 1] == 0 && extendCells[oldIndex + 1] == 0) ||
                        (extendCells[oldIndex - 1] == 1 && extendCells[oldIndex + 1] == 1)) {
                    extendCells[newIndex] = 1;
                }else{
                    extendCells[newIndex] = 0;
                }
            }
        }

        int[] result = new int[cellNum];
        System.arraycopy(extendCells, newOffset, result, 0, cellNum);
        return result;
    }

    public static void main(String[] args){
        int[] cells = {1,0,0,1,0,0,1,0};
        int[] result = new PrisonCellsAfterNDays().prisonAfterNDays(cells, 1000000000);
        System.out.println(result);
    }

}
