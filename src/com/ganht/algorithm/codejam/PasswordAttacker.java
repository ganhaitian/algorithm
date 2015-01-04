package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.Arrays;

/**
 * Created by gan on 2014/12/29.
 */
public class PasswordAttacker extends CodeJamCase {

    public static void main(String[] args) {
        new PasswordAttacker().parseInput(
            new File("C:\\Users\\gan\\Downloads\\A-small-practice (2).in"),
            new InputCaseLineParser() {
                @Override
                public void parseLine(int lineNumber, String line) {
                    if (lineNumber >= 2) {

                        String[] linePart = line.split(" ");
                        int M = Integer.parseInt(linePart[0]);
                        int N = Integer.parseInt(linePart[1]);
                        int maxOccurTimes = N - M + 1;
                        int[] tmpOccur = new int[M];
                        Arrays.fill(tmpOccur,0);
                        int pwdNums = countPossiblePwdNum(M, N, tmpOccur,maxOccurTimes,"");

                        System.out.println(String.format("Case #%d: %d", lineNumber - 1, pwdNums));
                    }
                }
            });
    }

    private static int countPossiblePwdNum(int M, int N,int[] tmpOccur,int maxOccurTimes,String tmpStr) {
        System.out.println("N:"+N + ",tmpStr:" + tmpStr);
        if (N == 0) {
            for(int occurTime:tmpOccur){
                if(occurTime == 0)
                    return 0;
            }
            System.out.println(tmpStr);
            return 1;
        }
        int pwdNums = 0;
        for (int i = 0; i < M; i++) {
            int[] copyTmpOccur = Arrays.copyOf(tmpOccur,tmpOccur.length);
            if(copyTmpOccur[i] + 1 > maxOccurTimes){
                continue;
            }else{
                copyTmpOccur[i] = copyTmpOccur[i] + 1;
            }
            pwdNums += countPossiblePwdNum(M, N - 1, copyTmpOccur,maxOccurTimes,tmpStr + i);
        }
        return pwdNums;
    }


    @Override
    protected void runCase() {

    }
}
