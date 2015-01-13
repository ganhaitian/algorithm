package com.ganht.algorithm.codejam;

import java.io.File;

/**
 * Created by gan on 2015/1/13.
 */
public class ParenthesesOrder extends CodeJamCase {

    @Override
    protected void runCase() {

    }

    public static void main(String[] args) {
        new ParenthesesOrder().parseInput(
            new File("C:\\Users\\gan\\Downloads\\D-small-practice.in"),
            new InputCaseLineParser() {
                @Override
                public void parseLine(int lineNumber, String line) {
                    if(lineNumber == 1)
                        return;
                    String[] linePart = line.split(" ");
                    int n = Integer.parseInt(linePart[0]);
                    int k = Integer.parseInt(linePart[1]);
                    String kthStr = getKthSmallest(n,k);
                    System.out.println(String.format("Case #%d: %s",lineNumber,kthStr));
                }
            });
    }

    public static String getKthSmallest(int n,int k){
        StringBuffer tmpStr = new StringBuffer();
        StringBuffer result = new StringBuffer();
        int sum = d(n,n,k,0,tmpStr,result);
        if(sum != -1 && sum < k)
            return "Doesn't Exist!";
        return result.toString();
    }

    public static int d(int n1,int n2,int k,int accu,StringBuffer tmpValue,StringBuffer result){

        if(n1 == 0){
            for(int i = 0;i < n2;i++)
                tmpValue.append(")");
            result.append(tmpValue);
            return 1;
        }

        if(n2 == 0){
            for(int i = 0;i < n1;i++)
                tmpValue.append("(");
            result.append(tmpValue);
            return 1;
        }

        StringBuffer tmpBuffer = new StringBuffer(tmpValue);
        int sum = 0;
        if(accu <= n1)
            sum += d(n1 - 1,n2,k,accu + 1, tmpBuffer.append("("),result);

        if(sum == k){
            return k;
        }

        if(accu - 1 >= 0){
            tmpBuffer = new StringBuffer(tmpValue);
            sum += d(n1,n2-1,k,accu - 1, tmpBuffer.append(")"),result);
        }

        if( sum == k) {
            return k;
        }

        return sum;
    }

}
