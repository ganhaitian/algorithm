package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        subGenerateParenthesis(n * 2, n * 2, 0, 0, "", result);
        return result;
    }

    private void subGenerateParenthesis(int n, int max, int accuLeft, int accuRight, String accString, List<String> result) {
        if (n <= 0) {
            result.add(accString);
            return;
        }

        int newAccuLeft = 0;
        int newAccuRight = 0;
        char[] ps = {'(', ')'};
        for (char p : ps) {
            if (p == '(') {
                newAccuLeft = accuLeft + 1;
                if (newAccuLeft <= (max / 2)) {
                    subGenerateParenthesis(n - 1, max, newAccuLeft, accuRight, accString + "(", result);
                }
            } else if (p == ')') {
                newAccuRight = accuRight + 1;
                if (newAccuRight <= (max / 2) && newAccuRight < newAccuLeft) {
                    subGenerateParenthesis(n - 1, max, accuLeft, newAccuRight, accString + ")", result);
                }
            }
        }
    }

    public static void main(String[] args) {
        new GenerateParentheses().generateParenthesis(5);
    }

}
