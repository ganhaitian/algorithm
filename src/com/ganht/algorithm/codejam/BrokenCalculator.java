package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem
 *
 * Alice is a smart student who is very good at math. She is attending a math class. In this
 * class, the teacher is teaching the students how to use a calculator. The teacher will tell
 * an integer to all of the students, and the students must type that exact number into their
 * calculators. If someone fails to type the number, he or she will be punished for failing
 * such an easy task!
 *
 * Unfortunately, at the beginning of the class, Alice finds that her calculator is broken! She
 * finds that some of the number buttons are totally broken, and only the "multiply" and
 * "equals" operator buttons are available to use. So she can only use these buttons to get
 * the number quickly.
 *
 * For instance, the teacher may say the number "60", while Alice's calculator can only type
 * "1", "2" and "5". She could push the following buttons:
 *
 *  Button "15" (2 clicks)
 *  Button "multiply" (1 click)
 *  Button "2" (1 click)
 *  Button "multiply" (1 click)
 *  Button "2" (1 click)
 *  Button "equals" (1 click)
 *
 * This method requires 7 button clicks. However, if Alice uses "12*5=", only 5 clicks are
 * needed. Of course Alice wants to get the integer as fast as possbile, so she wants to
 * minimize the number of button clicks. Your task is to help her find a way to get the
 * required number quickly.
 *
 * Input
 *
 * The first line of the input gives a number T, the number of integers the teacher says. T
 * test cases follow.
 *
 * Each case contains two lines. The first line contains ten numbers each of which is only 0
 * or 1. the ith number (starting from 0) is "1" if the number i can be clicked, or "0" if it is
 * broken. The second line contains only one number X, the integer the teacher tells
 * everyone.
 *
 * Output
 *
 * For each test case, output one line containing "Case #x: y", where x is the test case
 * number (starting from 1) and y is the minimum number of button clicks needed, or
 * "Impossible" if it is not possible to produce the number.
 *
 * Limits
 *
 * 1 ≤ T ≤ 100.
 *
 * Small dataset
 *
 * 1 ≤ X ≤ 100.
 *
 * Large dataset
 *
 * 1 ≤ X ≤ 106.
 *
 * Created by gan on 2015/1/17.
 */
public class BrokenCalculator extends CodeJamCase{


    @Override
    protected void runCase() {

    }

    public static int getMinClickNum(List<Integer> goodKeys,int targetNum){
        String targetNumStr = String.valueOf(targetNum);
        boolean allDone = true;
        for(Integer goodKey:goodKeys){
            if(targetNumStr.indexOf(String.valueOf(goodKey)) < 0){
                allDone = false;
                break;
            }
        }

        if(allDone){
            return targetNumStr.length();
        }

        for(int factor = 0;factor < targetNum;factor ++){

        }

        return 0;
    }

    public static void main(String[] args){
        new BrokenCalculator().parseInput(
            new File(""),
            new InputCaseBlockParser() {
            @Override
            public void parseLine(int caseIndex, String[] line) {

                String[] keyDesp = line[0].split(" ");
                List<Integer> goodKeys = new ArrayList<Integer>();
                for(int key = 0; key < 10;key ++){
                    if(Integer.parseInt(keyDesp[key]) == 1){
                        goodKeys.add(key);
                    }
                }

                int minClickNum = getMinClickNum(goodKeys,Integer.parseInt(line[1]));
                if(minClickNum == 0){
                    System.out.println(
                        String.format("Case #%d: %s",caseIndex,"Impossible"));
                }else
                    System.out.println(
                        String.format("Case #%d: %d",caseIndex,minClickNum));
            }

            @Override
            public int getCaseLineNumber() {
                return 2;
            }
        });
    }

}
