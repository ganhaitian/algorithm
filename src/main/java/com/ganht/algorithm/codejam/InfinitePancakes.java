package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Administrator on 2015/4/11.
 */
public class InfinitePancakes extends CodeJamCase {

    private int getSmallestTime(List<Integer> p, int D) {

        TreeMap<Float, Integer> pOrderMap = new TreeMap<Float, Integer>();
        for (Integer pNum : p) {
            if (!pOrderMap.containsKey((float) pNum)) {
                pOrderMap.put((float) pNum, 1);
            } else {
                pOrderMap.put((float) pNum, pOrderMap.get((float) pNum) + 1);
            }
        }

        // 累计的移动消耗时间
        int accuMoveSpend = 0;
        float smallestTime = pOrderMap.lastKey();
        while (true) {

            Float maxP = pOrderMap.lastKey();
            Integer maxPNum = pOrderMap.get(maxP);
            pOrderMap.remove(maxP);

            float secondMaxP = 0f;
            if (pOrderMap.size() > 0) {
                secondMaxP = pOrderMap.lastKey();
            }
            float newMaxP = Math.max((maxP / 2), secondMaxP);
            accuMoveSpend += maxPNum;
            float spendTime = newMaxP + accuMoveSpend;
            if (spendTime <= smallestTime) {
                smallestTime = spendTime;
                pOrderMap.put(maxP / 2, maxPNum * 2);
            } else {
                return (int) Math.ceil(smallestTime);
            }
        }

    }


    @Override
    protected void runCase() {
        parseInput(new File("C:\\Users\\Administrator\\Downloads\\B-small-attempt1.in"
        ), new InputCaseBlockParser() {
            @Override
            public void parseLine(int caseIndex, String[] line) {
                int D = Integer.parseInt(line[0]);

                List<Integer> p = new ArrayList<Integer>();
                for (String pStr : line[1].split(" ")) {
                    p.add(Integer.parseInt(pStr));
                }

                int smallestTime = getSmallestTime(p, D);
                System.out.println(String.format("Case #%d: %d",caseIndex,smallestTime));
            }

            @Override
            public int getCaseLineNumber() {
                return 2;
            }
        });
    }

    public static void main(String[] args) {
        new InfinitePancakes().runCase();
    }
}
