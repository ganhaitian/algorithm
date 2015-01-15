package com.ganht.algorithm.codejam;

import java.io.File;

/**
 * Created by gan on 2015/1/13.
 */
public class ParenthesesOrder extends CodeJamCase {

    @Override
    protected void runCase() {

    }

    public static String result = null;

    public static void main(String[] args) {
        new ParenthesesOrder().parseInput(
            new File("C:\\Users\\gan\\Downloads\\D-large-practice.in"),
            new InputCaseLineParser() {
                @Override
                public void parseLine(int lineNumber, String line) {
                    if (lineNumber == 1)
                        return;
                    String[] linePart = line.split(" ");
                    int n = Integer.parseInt(linePart[0]);
                    long k = Long.parseLong(linePart[1]);
                    String kthStr = getKthSmallest(n, k);
                    System.out.println(String.format("Case #%d: %s", lineNumber - 1, kthStr));
                }
            });
    }

    public static String getKthSmallest(int n, long k) {
        StringBuffer tmpStr = new StringBuffer();
        try {
            int sum = d(n, n, n, k, 0, 0, tmpStr);
            if (sum < k)
                return "Doesn't Exist!";
        } catch (Exception e) {

        }
        return result.toString();
    }

    /**
     * 效率太差，large-pratice.in过不去
     * @param n
     * @param n1
     * @param n2
     * @param k
     * @param accu
     * @param accuSum
     * @param tmpValue
     * @return
     */
    public static int d(int n, int n1, int n2, long k, int accu, int accuSum, StringBuffer tmpValue) {

        if (n1 == 0) {
            for (int i = 0; i < n2; i++)
                tmpValue.append(")");
            result = tmpValue.toString();
            return 1;
        }

        if (n2 == 0) {
            for (int i = 0; i < n1; i++)
                tmpValue.append("(");
            result = tmpValue.toString();
            return 1;
        }

        StringBuffer tmpBuffer = new StringBuffer(tmpValue);
        int sum = 0;
        if (accu <= n) {
            sum += d(n, n1 - 1, n2, k, accu + 1, accuSum, tmpBuffer.append("("));
            accuSum += sum;
        }

        if (accuSum == k) {
            throw new IllegalStateException();
        }

        if (accu - 1 >= 0) {
            tmpBuffer = new StringBuffer(tmpValue);
            int t = d(n, n1, n2 - 1, k, accu - 1, accuSum, tmpBuffer.append(")"));
            sum += t;
            accuSum += t;
        }

        if (accuSum == k) {
            throw new IllegalStateException();
        }

        return sum;
    }

}
