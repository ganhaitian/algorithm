package com.ganht.algorithm.leetcode;

import java.util.HashMap;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 *
 * Example 1:
 *
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 *
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 *
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 *
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * @author haitian.gan
 */
public class IntegerTtoEnglishWords {

    // 这题挺垃圾的，不知道为什么是hard难度，明明没啥hard，可能是美国人的数学比较弱吧
    public String numberToWords(int num) {
        var prefixMap = new HashMap<Integer, String>();
        prefixMap.put(1, "Thousand");
        prefixMap.put(2, "Million");
        prefixMap.put(3, "Billion");

        var numberMap = new HashMap<Integer, String>();
        numberMap.put(1, "One");
        numberMap.put(2, "Two");
        numberMap.put(3, "Three");
        numberMap.put(4, "Four");
        numberMap.put(5, "Five");
        numberMap.put(6, "Six");
        numberMap.put(7, "Seven");
        numberMap.put(8, "Eight");
        numberMap.put(9, "Nine");
        numberMap.put(10, "Ten");


        String numStr = String.valueOf(num);
        int prefix = 0;
        for (int i = numStr.length() - 1;; i = i - 2) {
            int start = Math.max(i - 2, 0);
            String part = numStr.substring(start, i);
            /*for(int i = 0;i < ){

            }*/

            if(start <= 0){
                break;
            }

            prefix ++;
        }

        return "";
    }

}
