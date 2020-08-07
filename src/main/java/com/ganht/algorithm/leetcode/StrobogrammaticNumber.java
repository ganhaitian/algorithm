package com.ganht.algorithm.leetcode;

/**
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 *
 * Example 1:
 *
 * Input:  "69"
 * Output: true
 * Example 2:
 *
 * Input:  "88"
 * Output: true
 * Example 3:
 *
 * Input:  "962"
 * Output: false
 * @author haitian.gan
 */
public class StrobogrammaticNumber {

    public boolean isStrobogrammatic(String num) {
        int comparePos = num .length() % 2 == 0 ? (num.length() / 2 - 1) : num.length() / 2;
        for (int i = 0; i <= comparePos; i++) {
            char number = num.charAt(i);
            char oppositeNum = num.charAt(num.length() - i - 1);

            if(number == oppositeNum && (number != '0' && number != '8' && number != '1')){
                return false;
            }else if(number != oppositeNum && !((number == '6' && oppositeNum == '9') || (number == '9' && oppositeNum == '6'))){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        boolean result = new StrobogrammaticNumber().isStrobogrammatic("1");
        System.out.println(result);
    }

}
