package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2015/3/2.
 */
public class TheRepeater extends CodeJamCase {

    private int findMinMoves(String[] lines) {

        Map<Character, List<Integer>> accuCounter = new HashMap<Character, List<Integer>>();
        List<Character> existChars = new ArrayList<Character>();
        for (String line : lines) {
            List<Character> curChars = new ArrayList<Character>();
            Map<Character, AtomicInteger> cCounter = new HashMap<Character, AtomicInteger>();
            for (char c : line.toCharArray()) {
                if (!curChars.contains(c)) {
                    curChars.add(c);
                }
                if (cCounter.get(c) == null)
                    cCounter.put(c, new AtomicInteger(1));
                else
                    cCounter.get(c).incrementAndGet();
            }

            if (existChars.size() > 0) {
                if (!existChars.equals(curChars))
                    return -1;
            } else
                existChars = curChars;

            for (Map.Entry<Character, AtomicInteger> entry : cCounter.entrySet()) {
                if (accuCounter.get(entry.getKey()) == null) {
                    accuCounter.put(entry.getKey(), new ArrayList<Integer>());
                }
                accuCounter.get(entry.getKey()).add(entry.getValue().get());
            }
        }

        int minActions = 0;
        for (Map.Entry<Character, List<Integer>> entry : accuCounter.entrySet()) {
            List<Integer> accuNums = entry.getValue();
            Collections.sort(accuNums);

            /*int middle = accuNums.get(accuNums.size() / 2);
            for (int i = 0; i < accuNums.size(); i++) {
                minActions += Math.abs(middle - accuNums.get(i));
            }
*/
            int tmpMin = 0;
            int tmpSum = 0;
            for (int i = 0; i < accuNums.size(); i++) {
                for (int j = 0; j < accuNums.size(); j++) {
                    tmpSum += Math.abs(accuNums.get(j) - accuNums.get(i));
                }
                if(tmpMin == 0)
                    tmpMin = tmpSum;
                else if(tmpSum < tmpMin)
                    tmpMin = tmpSum;
            }
            minActions += tmpMin;
        }
        return minActions;
    }

    @Override
    protected void runCase() {
        parseAdaptiveInput(new File("C:\\Users\\Administrator\\Downloads\\A-small-practice (2).in"),
                new InputCaseBlockParser() {
                    @Override
                    public void parseLine(int caseNumber, String[] lines) {
                        int minMoves = findMinMoves(lines);
                        if (minMoves >= 0) {
                            System.out.println(String.format("Case #%d: %d", caseNumber, minMoves));
                        } else {
                            System.out.println(String.format("Case #%d: %s", caseNumber, "Fegla Won"));
                        }
                    }

                    @Override
                    public int getCaseLineNumber() {
                        return -1;
                    }
                });
    }

    public static void main(String[] args) {
        new TheRepeater().runCase();
    }
}
