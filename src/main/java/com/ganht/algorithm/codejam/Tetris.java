package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem
 * <p/>
 * Tetris is a famous video game that almost everyone has played it. In this problem, you
 * need to simulate a simplified version of it.
 * <p/>
 * In our version, the game is played in a W by H field with gravity. At the beginning, the
 * field is empty. Then the tetrominos start to fall from above top of the field to bottom of the
 * field, one by one. Each tetromino will stop as soon as it touches some other tetrominos
 * or bottom of the field.
 * <p/>
 * One interesting feature of the game is called "line clearing". A line will be cleared as soon
 * as it is filled by tetrominos. More than one line may be cleared at a time. For example:
 * <p/>
 * |..............|      |..............|      |..............|
 * |.............o|      |..............|      |..............|
 * |.............o|      |..............|      |..............|
 * |.............o|      |..............|      |..............|
 * |.............o|      |..............|      |..............|
 * |..xx..........| -->  |..xx..........| -->  |..............|
 * |xxxxxxxxxxxxx.|      |xxxxxxxxxxxxxo|      |..............|
 * |xxxxxxxxxxxxx.|      |xxxxxxxxxxxxxo|      |..xx..........|
 * |xx..xxxxxxxxx.|      |xx..xxxxxxxxxo|      |xx..xxxxxxxxxo|
 * |xxxxxxxxxxx...|      |xxxxxxxxxxx..o|      |xxxxxxxxxxx..o|
 * ----------------      ----------------      ----------------
 * <p/>
 * Note that in this simplified version, the "floating" tetromino blocks won't continue to fall
 * after lines are cleared. This is why the top-most two squares will keep in such position.
 * Consequently, cascade clearing won't happen, even though it would happen in the original
 * version of Tetris.
 * <p/>
 * The game ends when all the given tetrominos are placed, or the current tetromino cannot
 * be placed due to the height limit of the field is reached.
 * <p/>
 * In this problem, each tetromino will has its type, rotation and falling position told by the
 * input. They will start to fall from the above of the field. Your goal is to simulate and get
 * the final result of each play.
 * <p/>
 * Input
 * We have 7 types of tetromino:
 * <p/>
 * 1   2   3   4   5   6   7
 * <p/>
 * x    x  x    x  xx  x    x
 * xx  xx  x    x  xx  x   xxx
 * x  x   xx  xx      x
 * x
 * <p/>
 * Rotation of a tetromino is represented by a number r. r can be 0, 1, 2 or 3. Rotation is
 * counterclockwise. For example:
 * <p/>
 * r=0   r=1  r=2   r=3
 * <p/>
 * x     x   xxx   x
 * xxx   xx    x    xx
 * x         x
 * <p/>
 * x     xx   x     xx
 * xx   xx    xx   xx
 * x          x
 * <p/>
 * <p/>
 * The horizontal falling position is represented by a number x. It is the coordinate of the
 * lower left square of a tetromino's bounding box. Here x starts from 0.
 * <p/>
 * The first line of the input gives the number of test cases, T. For each test case, the first
 * line of input has 3 integers, W, H, N. W is the width, H is the height and N is the number
 * of blocks that are going to fall.
 * <p/>
 * Then N lines below, each line has 3 integers, ti, ri, xi. ti tells the tetromino type. ri is the
 * rotation of this tetromino. xi is the horizontal falling position of this tetromino. It is
 * guaranteed that xi will make the tetromino inside the field, horizontally.
 * <p/>
 * Output
 * <p/>
 * For each test case, first output one line containing "Case #i:", where i is the test case
 * number (starting from 1). And then, if the game ends before the N blocks, output "Game
 * Over!"(without quotes). Otherwise, output the game field's final state, which should have
 * H lines, each has W characters. Each character can be '.' or 'x'.
 * <p/>
 * <p/>
 * Created by gan on 2015/1/20.
 */
public class Tetris extends CodeJamCase {

    @Override
    protected void runCase() {

        final AtomicInteger lineIndex = new AtomicInteger(0);
        final AtomicInteger W = new AtomicInteger(0);
        final AtomicInteger H = new AtomicInteger(0);
        final AtomicInteger N = new AtomicInteger(0);
        final List<String> tetrominoTypes = new ArrayList<String>();
        final AtomicInteger caseIndex = new AtomicInteger(0);

        parseInput(new File("C:\\Users\\gan\\Downloads\\D-large-practice (1).in"),
            new InputCaseLineParser() {
                @Override
                public void parseLine(int lineNumber, String line) {
                    if (lineNumber == 1)
                        return;
                    if (lineIndex.get() == 0) {
                        String[] inputParts = line.split(" ");
                        W.set(Integer.parseInt(inputParts[0]));
                        H.set(Integer.parseInt(inputParts[1]));
                        N.set(Integer.parseInt(inputParts[2]));

                    } else {
                        tetrominoTypes.add(line);
                    }

                    if (lineIndex.get() == N.get()) {

                        List<String> finalStateLines = getFinalState(W.get(), H.get(), tetrominoTypes);
                        System.out.println(String.format("Case #%d:", caseIndex.incrementAndGet()));

                        if (finalStateLines == null) {
                            System.out.println("Game Over!");
                        } else {
                            for (String finalStateLine : finalStateLines) {
                                System.out.println(finalStateLine);
                            }
                        }

                        tetrominoTypes.clear();
                        lineIndex.set(0);
                        W.set(0);
                        H.set(0);
                        N.set(0);
                    } else
                        lineIndex.incrementAndGet();
                }

            });
    }

    private class Cell {
        boolean fill;
    }

    private Integer[][] rotateT(Integer[][] input) {
        Integer[][] output = new Integer[input[0].length][input.length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                output[input[0].length - j - 1][i] = input[i][j];
            }
        }
        return output;
    }

    private class Field {

        List<Integer> lineStatus = new ArrayList<Integer>();
        List<Cell[]> cells = new ArrayList<Cell[]>();
        private int W, H;

        public Field(int W, int H) {
            for (int h = 0; h < H; h++) {
                Cell[] wLine = new Cell[W];
                for (int w = 0; w < W; w++) {
                    wLine[w] = new Cell();
                }
                cells.add(wLine);
                //初始化每行的填充数据
                lineStatus.add(0);
            }
            this.W = W;
            this.H = H;
        }

        public Cell getCell(int h, int w) {
            return cells.get(h)[w];
        }

        public void fill(int h, int w) {
            cells.get(h)[w].fill = true;
            Integer fillNum = lineStatus.get(h);
            fillNum = fillNum + 1;
            lineStatus.set(h, fillNum);
            if (fillNum == W) {
                lineStatus.remove(h);
                lineStatus.add(0, 0);

                cells.remove(h);
                Cell[] wLine = new Cell[W];
                for (int _w = 0; _w < W; _w++) {
                    wLine[_w] = new Cell();
                }
                cells.add(0, wLine);
            }
        }

        public List<String> toStringList() {
            List<String> stringList = new ArrayList<String>();
            for (int h = 0; h < H; h++) {
                StringBuilder line = new StringBuilder();
                for (int w = 0; w < W; w++) {
                    if (cells.get(h)[w].fill) {
                        line.append("x");
                    } else {
                        line.append(".");
                    }
                }
                stringList.add(line.toString());
            }
            return stringList;
        }

    }

    private List<String> getFinalState(int W, int H, List<String> tetroTypes) {

        Field field = new Field(W, H);

        Integer[][] t1 = {{1, 0}, {1, 1}, {0, 1}};
        Integer[][] t2 = {{0, 1}, {1, 1}, {1, 0}};
        Integer[][] t3 = {{1, 0}, {1, 0}, {1, 1}};
        Integer[][] t4 = {{0, 1}, {0, 1}, {1, 1}};
        Integer[][] t5 = {{1, 1}, {1, 1}};
        Integer[][] t6 = {{1}, {1}, {1}, {1}};
        Integer[][] t7 = {{0, 1, 0}, {1, 1, 1}};

        List<Integer[][]> tList = new ArrayList<Integer[][]>();
        tList.add(t1);
        tList.add(t2);
        tList.add(t3);
        tList.add(t4);
        tList.add(t5);
        tList.add(t6);
        tList.add(t7);

        for (String tetroType : tetroTypes) {

            String[] tetroPart = tetroType.split(" ");
            int t = Integer.parseInt(tetroPart[0]);
            int r = Integer.parseInt(tetroPart[1]);
            int x = Integer.parseInt(tetroPart[2]);

            Integer[][] tetro = tList.get(t - 1);
            for (int i = 0; i < r; i++) {
                tetro = rotateT(tetro);
            }

            if (tetro.length > H)
                return null;

            int tetroHeight = tetro.length;
            int tetroWidth = tetro[0].length;
            int stayPos = 0;
            boolean stop = false;
            outer:
            for (int i = 0; i <= H - tetroHeight; i++) {
                stayPos = i;
                for (int k = tetroHeight - 1; k >= 0; k--) {
                    for (int j = 0; j <= tetroWidth - 1; j++) {
                        if (field.getCell(k + i, j + x).fill && tetro[k][j] == 1) {
                            if(stayPos == 0)
                                stop = true;
                            else{
                                stayPos--;
                            }
                            break outer;
                        }
                    }
                }
            }

            if(stop)
                return null;

            for (int i = 0; i <= tetroHeight - 1; i++) {
                for (int j = 0; j <= tetroWidth - 1; j++) {
                    if (tetro[i][j] == 1) {
                        field.fill(stayPos + i, x + j);
                    }
                }
            }

        }

        return field.toStringList();
    }

    public static void main(String[] args) {
        new Tetris().runCase();
    }
}
