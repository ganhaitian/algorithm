package com.ganht.algorithm.leetcode;

/**
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence
 * of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string
 * end, return True if and only if there exists a sequence of moves to transform one string to the other.
 *
 * Example:
 *
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: True
 * Explanation:
 * We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 *
 *
 * Constraints:
 *
 * 1 <= len(start) == len(end) <= 10000.
 * Both start and end will only consist of characters in {'L', 'R', 'X'}.
 * @author haitian.gan
 */
public class SwapAdjacentInLRString {

    public boolean canTransform1(String start, String end) {
        if (!start.replace("X", "").equals(end.replace("X", "")))
            return false;

        int t = 0;
        for (int i = 0; i < start.length(); ++i)
            if (start.charAt(i) == 'L') {
                while (end.charAt(t) != 'L') t++;
                if (i < t++) return false;
            }

        t = 0;
        for (int i = 0; i < start.length(); ++i)
            if (start.charAt(i) == 'R') {
                while (end.charAt(t) != 'R') t++;
                if (i > t++) return false;
            }

        return true;
    }

    public boolean canTransform(String start, String end) {
       return doCanTransform(start, end) || doCanTransform(end, start);
    }

    private boolean doCanTransform(String start,String end){
        char[] sChars = start.toCharArray();
        char[] eChars = end.toCharArray();

        int i = 0;
        while (i < start.length()) {
            char s1 = sChars[i];
            char e1 = eChars[i];
            if (s1 == e1) {
                i++;
            } else if (i < start.length() - 1) {
                if ((sChars[i] == 'X' && sChars[i + 1] == 'L') || (sChars[i] == 'R' && sChars[i + 1] == 'X')) {
                    // 交换下
                    sChars[i]     = sChars[i + 1];
                    sChars[i + 1] = s1;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SwapAdjacentInLRString().canTransform("XXXXXLXXXX", "LXXXXXXXXX"));
    }

}
