package com.ganht.algorithm.codejam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganhaitian on 2015/4/11.
 */
public class StandingOvation extends CodeJamCase {


    @Override
    protected void runCase() {
        parseInput(new File("C:\\Users\\ganhaitian\\Downloads\\A-large.in"),
                new InputCaseLineParser() {
                    @Override
                    public void parseLine(int lineNumber, String line) {
                        if (lineNumber == 1)
                            return;
                        else {
                            String[] inputParts = line.split(" ");

                            int sMaxLvl = Integer.parseInt(inputParts[0]);
                            // shyness list.
                            List<Integer> sList = new ArrayList<Integer>();
                            for (char sChar : inputParts[1].toCharArray()) {
                                sList.add(Character.digit(sChar, 10));
                            }

                            int minNumsOfFriends = getMinNumsOfFriends(sList, sMaxLvl);
                            System.out.println(String.format("Case #%d: %d", lineNumber - 1, minNumsOfFriends));
                        }
                    }
                });
    }


    private int getMinNumsOfFriends(List<Integer> sList, int sMaxLvl) {
        int ovationNums = 0;
        int inviteNums = 0;
        for (int sLvl = 0; sLvl <= sMaxLvl; sLvl++) {
            if (sLvl > 0 && sList.get(sLvl) > 0) {
                inviteNums += Math.max(sLvl - ovationNums, 0);
                ovationNums += inviteNums;
            }
            ovationNums += sList.get(sLvl);
        }
        return inviteNums;
    }

    public static void main(String[] args) {
        new StandingOvation().runCase();
    }
}
