package com.ganht.algorithm.codejam;

/**
 * Created by ganhaitian on 2015/6/12.
 */
public class NoisyNeighbors extends CodeJamCase {

    public int getMinUnhappinessNum(int R, int C, int N) {
        int total = R * C;
        // 如果住户数不足所有房间数的二分之一，则完全可以隔开，最小值为零
        if (Math.ceil(total / 2d) >= N) {
            return 0;
        }
        return 0;
    }

    @Override
    protected void runCase() {

    }

    public static void main(String[] args) {
        new NoisyNeighbors().runCase();
    }
}
