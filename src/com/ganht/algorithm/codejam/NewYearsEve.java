package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.*;

/**
 * Created by gan on 2014/12/29.
 */
public class NewYearsEve extends CodeJamCase {

    private static List<Integer> glassNumsCache = new ArrayList<Integer>(400);

    static {
        glassNumsCache.add(1);
    }

    public static void main(String[] args) {
        new NewYearsEve().parseInput(
            new File("C:\\Users\\gan\\Downloads\\B-large-practice.in"),
            new InputCaseLineParser() {
                @Override
                public void parseLine(int lineNumber, String line) {
                    if (lineNumber >= 2) {
                        String[] lineParts = line.split(" ");
                        int B = Integer.parseInt(lineParts[0]);
                        int L = Integer.parseInt(lineParts[1]);
                        int N = Integer.parseInt(lineParts[2]);
                        double quantityInGlass = calQuantityInGlass1(B, L, N);
                        System.out.println(String.format("Case #%d: %f", lineNumber - 1, quantityInGlass));
                    }
                }
            });
    }

    public static Map<Position, Double> volumeCache = new HashMap<Position, Double>();

    public static class Position {
        int L;
        int N;

        public Position(int L, int N) {
            this.L = L;
            this.N = N;
        }

        public boolean equals(Object target) {
            if (target != null && target instanceof Position) {
                Position p = (Position) target;
                if (L == p.L && N == p.N)
                    return true;
            }
            return false;
        }

        public int hashCode() {
            return String.valueOf(L).hashCode() + String.valueOf(N).hashCode();
        }
    }

    /**
     * 递归解决，某个杯子流入的量，是上层父杯子溢出量乘1/3，一层层递归到第一层
     * 某个杯子和父杯子序号的对应关系要归纳下
     * @param B
     * @param L
     * @param N
     * @return
     */
    private static double getVolumeShare(int B, int L, int N) {

        if (L == 1) {
            return (B * 750d);
        }

        int maxIndexInRow = 0;
        List<Integer> parentGlassIndexes = new ArrayList<Integer>();

        for (int i = 1; i <= L; i++) {
            maxIndexInRow = (i * (i + 1) / 2);
            if (N <= maxIndexInRow) {
                if (N == 1) {
                    parentGlassIndexes.add(1);
                } else if (N == maxIndexInRow) {
                    parentGlassIndexes.add(N - i);
                } else if (N == (maxIndexInRow - i + 1)) {
                    parentGlassIndexes.add(N - (i - 1));
                } else {
                    parentGlassIndexes.add(N - i);
                    parentGlassIndexes.add(N - (i - 1));
                }
                if (i > 1 && i < L)
                    parentGlassIndexes.add(N);
                break;
            }
        }

        double volumeShare = 0;

        for (Integer parentGlassIndex : parentGlassIndexes) {
            Position parentPos = new Position(L - 1, parentGlassIndex);
            Double parentVolumeShare = volumeCache.get(parentPos);
            if (parentVolumeShare == null) {
                parentVolumeShare = getVolumeShare(B, L - 1, parentGlassIndex);
                volumeCache.put(parentPos, parentVolumeShare);
            }
            volumeShare += Math.max((parentVolumeShare - 250d) / 3d,0);
        }

        volumeCache.put(new Position(L, N), volumeShare);
        return volumeShare;
    }


    private static double calQuantityInGlass1(int B, int L, int N) {
        volumeCache.clear();
        return Math.min(getVolumeShare(B, L, N), 250d);
    }

    //以下错的很离谱，没有考虑到每个杯子注入液体的速度是不一样的。
    //并不是一层的杯子都流满才会进入下一层。
    private static double calQuantityInGlass(int B, int L, int N) {

        if (L == 1) {
            return 250;
        }

        if (L == 2 && B == 1) {
            return 500 / 3d;
        }

        if (L == 2 && B > 1) {
            return 250d;
        }

        int totalQuantity = B * 750;
        Integer glassNumToLvl = null;
        try {
            glassNumToLvl = glassNumsCache.get(L - 1);
        } catch (Exception e) {
        }

        if (glassNumToLvl == null) {
            int levelNum = glassNumsCache.size();
            int glassesToLvl = glassNumsCache.get(levelNum - 1);
            for (int l = levelNum + 1; l <= L; l++) {
                glassesToLvl += (l * (l + 1) / 2);
                glassNumsCache.add(glassesToLvl);
            }
            glassNumToLvl = glassNumsCache.get(L - 1);
        }

        if (glassNumToLvl * 250 < totalQuantity) {
            return 250;
        }

        //如果上一层可以装满
        if (glassNumsCache.get(L - 2) * 250 >= totalQuantity) {
            return 0;
        }

        int glassNumInLastLvl = glassNumsCache.get(L - 2) - glassNumsCache.get(L - 3);
        double quantityInGlass = (totalQuantity - glassNumsCache.get(L - 2) * 250) / (glassNumInLastLvl * 3d);

        int maxIndexInLvl = 0;
        int glassInLvl = 0;
        for (int i = 1; i <= L; i++) {
            maxIndexInLvl = (i * (i + 1) / 2);
            if (N <= maxIndexInLvl) {
                glassInLvl = i;
                break;
            }
        }
        int minIndexInLvl = maxIndexInLvl - glassInLvl + 1;

        int quantityMultiple = 1;
        if (N == 1) {
            quantityMultiple = 1;
        } else if (N == maxIndexInLvl || N == minIndexInLvl) {
            if (glassInLvl < L) {
                quantityMultiple = 2;
            } else {
                quantityMultiple = 1;
            }
        } else {
            quantityMultiple = 3;
        }

        return quantityInGlass * quantityMultiple;
    }


    @Override
    protected void runCase() {

    }
}
