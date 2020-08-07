package com.ganht.algorithm.leetcode;

import java.util.LinkedList;
import java.util.function.Function;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace
 * character.
 *
 * Example 1:
 *
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 *
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 *
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 *
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * Follow up:
 *
 * Can you solve it in O(N) time and O(1) space?
 * @author haitian.gan
 */
public class BackspaceStringCompare {

    public boolean backspaceCompare(String S, String T) {
        Function<String,String> transStrFunc = str -> {
            LinkedList<String> letterList = new LinkedList<>();
            for(char l : str.toCharArray()){
                if(l == '#'){
                    if(letterList.size() > 0){
                        letterList.removeLast();
                    }
                }else{
                    letterList.addLast(String.valueOf(l));
                }
            }

            return String.join("", letterList);
        };

        return transStrFunc.apply(S).equals(transStrFunc.apply(T));
    }

    public static void main(String[] args){
        boolean result = new BackspaceStringCompare().backspaceCompare("y#fo##f","y#f#o##f");
        System.out.println(result);
    }

}
