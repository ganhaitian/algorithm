package com.ganht.algorithm.leetcode;

import java.math.BigInteger;

/**
 *
 * Additive number is a string whose digits can form additive sequence.
 *
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number
 * in the sequence must be the sum of the preceding two.
 *
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 *
 *
 * Example 1:
 *
 * Input: "112358"
 * Output: true
 * Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 *              1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * Example 2:
 *
 * Input: "199100199"
 * Output: true
 * Explanation: The additive sequence is: 1, 99, 100, 199.
 *              1 + 99 = 100, 99 + 100 = 199
 *
 *
 * Constraints:
 *
 * num consists only of digits '0'-'9'.
 * 1 <= num.length <= 35
 * Follow up:
 * How would you handle overflow for very large input integers?
 *
 *   Created by ganhaitian on 2016/11/22.
 */
public class AdditiveNumber {

    // 二刷
    public boolean isAdditiveNumber2(String num){
        if(num.length() < 3){
            return false;
        }

        return isAdditiveNumber2(num , null, null);
    }

    // 多简洁，但其实还可以加一个备忘录
    private boolean isAdditiveNumber2(String str, BigInteger a, BigInteger b) {
        var expect = (a == null || b == null) ? null : a.add(b);
        for (int i = 1; i <= (str.startsWith("0") ? 1 : str.length()); i++) {
            var currNum = new BigInteger(str.substring(0, i));
            if (a != null && b != null) {
                int cmpResult = currNum.compareTo(expect);
                if (cmpResult < 0) {
                    continue;
                } else if (cmpResult > 0) {
                    return false;
                } else if (i >= str.length()) {
                    return true;
                }
            }

            if (i >= str.length()) {
                return false;
            }

            if (isAdditiveNumber2(str.substring(i), b, currNum)) {
                return true;
            }
        }

        return false;
    }

    public boolean isAdditiveNumber(String num) {
        return selectNextNum(0,0,num,-2);
    }

    // 这下面写的是什么鸡巴玩意儿，OMG
    private boolean selectNextNum(long preNum,long preSum,String str,int op){

        int nextOp = 0;
        if(op == -2){
            nextOp = -1;
        }else if(op == -1){
            nextOp = 0;
        }

        boolean cantTry = false;
        if(str.startsWith("0"))
            cantTry = true;

        String tmpSelectStr;
        long tmpSelectValue = 0;
        long tmpPreSum;
        for(int i = 1;i <= str.length();i ++){

            if(cantTry && i >= 2)
                break;

            tmpSelectStr = str.substring(0,i);

            try {
                tmpSelectValue = Long.parseLong(tmpSelectStr);
            }catch(NumberFormatException e ){
                System.out.println();
            }

            if(op == 0){
                if(tmpSelectValue < preSum)
                    continue;
                else if(tmpSelectValue > preSum)
                    break;

                if(i == str.length())
                    return true;
            }

            tmpPreSum = preNum + tmpSelectValue;
            if(selectNextNum(tmpSelectValue,tmpPreSum,str.substring(i,str.length()),nextOp))
                return true;
        }

        return false;
    }

    public static void main(String[] args){
        System.out.println(new AdditiveNumber().isAdditiveNumber2("19928"));
    }
}
