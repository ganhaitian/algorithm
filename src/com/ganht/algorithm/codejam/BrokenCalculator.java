package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Problem
 * <p/>
 * Alice is a smart student who is very good at math. She is attending a math class. In this
 * class, the teacher is teaching the students how to use a calculator. The teacher will tell
 * an integer to all of the students, and the students must type that exact number into their
 * calculators. If someone fails to type the number, he or she will be punished for failing
 * such an easy task!
 * <p/>
 * Unfortunately, at the beginning of the class, Alice finds that her calculator is broken! She
 * finds that some of the number buttons are totally broken, and only the "multiply" and
 * "equals" operator buttons are available to use. So she can only use these buttons to get
 * the number quickly.
 * <p/>
 * For instance, the teacher may say the number "60", while Alice's calculator can only type
 * "1", "2" and "5". She could push the following buttons:
 * <p/>
 * Button "15" (2 clicks)
 * Button "multiply" (1 click)
 * Button "2" (1 click)
 * Button "multiply" (1 click)
 * Button "2" (1 click)
 * Button "equals" (1 click)
 * <p/>
 * This method requires 7 button clicks. However, if Alice uses "12*5=", only 5 clicks are
 * needed. Of course Alice wants to get the integer as fast as possbile, so she wants to
 * minimize the number of button clicks. Your task is to help her find a way to get the
 * required number quickly.
 * <p/>
 * Input
 * <p/>
 * The first line of the input gives a number T, the number of integers the teacher says. T
 * test cases follow.
 * <p/>
 * Each case contains two lines. The first line contains ten numbers each of which is only 0
 * or 1. the ith number (starting from 0) is "1" if the number i can be clicked, or "0" if it is
 * broken. The second line contains only one number X, the integer the teacher tells
 * everyone.
 * <p/>
 * Output
 * <p/>
 * For each test case, output one line containing "Case #x: y", where x is the test case
 * number (starting from 1) and y is the minimum number of button clicks needed, or
 * "Impossible" if it is not possible to produce the number.
 * <p/>
 * Limits
 * <p/>
 * 1 ≤ T ≤ 100.
 * <p/>
 * Small dataset
 * <p/>
 * 1 ≤ X ≤ 100.
 * <p/>
 * Large dataset
 * <p/>
 * 1 ≤ X ≤ 106.
 * <p/>
 * Created by gan on 2015/1/17.
 */
public class BrokenCalculator extends CodeJamCase {


    @Override
    protected void runCase() {

    }

    public static int getMinClickNum(HashSet<Integer> goodKeys, int targetNum) {
        String targetNumStr = String.valueOf(targetNum);
        boolean allDone = true;

        for (char bit : targetNumStr.toCharArray()) {
            if (!goodKeys.contains(Integer.parseInt(String.valueOf(bit)))) {
                allDone = false;
                break;
            }
        }

        if (allDone) {
            return targetNumStr.length();
        }

        int minClickNum = 0;
        for (int factor = 2; factor <= (targetNum / 2); factor++) {
            if (targetNum % factor == 0) {
                int tmpMinNum1 = getMinClickNum(goodKeys, targetNum / factor);
                if (tmpMinNum1 == 0 || (minClickNum > 0 && tmpMinNum1 >= minClickNum))
                    continue;
                int tmpMinNum2 = getMinClickNum(goodKeys, factor);
                if (tmpMinNum2 == 0)
                    continue;
                int tmpMinNum = tmpMinNum1 + tmpMinNum2 + 1;
                if (minClickNum == 0 || tmpMinNum < minClickNum) {
                    minClickNum = tmpMinNum;
                }
            }
        }

        return minClickNum;
    }

    public static void main(String[] args) {
        new BrokenCalculator().parseInput(
            new File("C:\\Users\\gan\\Downloads\\C-large-practice (2).in"),
            new InputCaseBlockParser() {
                @Override
                public void parseLine(int caseIndex, String[] line) {

                    String[] keyDesp = line[0].split(" ");
                    HashSet<Integer> goodKeys = new HashSet<Integer>();
                    for (int key = 0; key < 10; key++) {
                        if (Integer.parseInt(keyDesp[key]) == 1) {
                            goodKeys.add(key);
                        }
                    }

                    int minClickNum = getMinClickNum(goodKeys, Integer.parseInt(line[1]));
                    if (minClickNum == 0) {
                        System.out.println(
                            String.format("Case #%d: %s", caseIndex, "Impossible"));
                    } else
                        System.out.println(
                            String.format("Case #%d: %d", caseIndex, minClickNum + 1));
                }

                @Override
                public int getCaseLineNumber() {
                    return 2;
                }
            });
    }

}
