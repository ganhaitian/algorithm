package com.ganht.algorithm.leetcode;

/**
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become
 * 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.
 *
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note
 * that the rotated number can be greater than the original number.)
 *
 * Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.
 *
 *
 *
 * Example 1:
 *
 * Input: 20
 * Output: 6
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19].
 * 6 converts to 9.
 * 9 converts to 6.
 * 10 converts to 01 which is just 1.
 * 16 converts to 91.
 * 18 converts to 81.
 * 19 converts to 61.
 * Example 2:
 *
 * Input: 100
 * Output: 19
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 *
 *
 * Note:
 *
 * 1 <= N <= 10^9
 * @author haitian.gan
 */
public class ConfusingNumberII {

    private int[] nums = {0, 1, 6, 8, 9};

    //perfect,wonderful
    public int confusingNumberII(int N) {
        return append(0, N);
    }

    private int append(long org, long N){
        int sum = 0;
        for(int num : nums){
            if(org == 0 && num == 0){
                continue;
            }

            long curr = org * 10 + num;
            if(curr > N){
                continue;
            }

            sum = sum + append(curr, N) + 1;
            if(isRotateEqual(curr)){
                sum --;
            }
        }

        return sum;
    }

    private boolean isRotateEqual(long num) {
        String numStr = String.valueOf(num);
        for (int i = 0; i < numStr.length(); i++) {
            char curr    = numStr.charAt(i);
            char symChar = numStr.charAt(numStr.length() - 1 - i);

            if (curr == '6' && symChar != '9') {
                return false;
            } else if (curr == '9' && symChar != '6') {
                return false;
            } else if (curr == '0' && symChar != '0') {
                return false;
            } else if (curr == '8' && symChar != '8') {
                return false;
            } else if (curr == '1' && symChar != '1') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        System.out.println(new ConfusingNumberII().confusingNumberII(1000000000));
    }

}
