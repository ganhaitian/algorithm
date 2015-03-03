package com.ganht.algorithm.codejam;

import java.io.File;

/**
 * Created by Administrator on 2015/3/2.
 */
public class TheRepeater extends CodeJamCase {

    private int findMinMoves(String[] lines){
        return 0;
    }

    @Override
    protected void runCase() {
        parseAdaptiveInput(new File(""),
                new InputCaseBlockParser() {
                    @Override
                    public void parseLine(int lineNumber, String[] lines) {
                        int minMoves = findMinMoves(lines);
                        if(minMoves > 0){
                            System.out.println(String.format("Case #%d: %s",lineNumber));
                        }else{
                            System.out.println(String.format("Case #%d: %s"," Fegla Won"));
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
