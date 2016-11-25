package com.ganht.algorithm.leetcode;

/**
 *   Additive number is a string whose digits can form additive sequence.

     A valid additive sequence should contain at least three numbers. Except for the first two numbers,
     each subsequent number in the sequence must be the sum of the preceding two.

     For example:
     "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

     1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
     "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
     1 + 99 = 100, 99 + 100 = 199
     Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

     Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

     Follow up:
     How would you handle overflow for very large input integers?
 *
 *   Created by ganhaitian on 2016/11/22.
 */
public class AdditiveNumber {

    public boolean isAdditiveNumber(String num) {
        return selectNextNum(0,0,num,-2);
    }

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
        System.out.println(new AdditiveNumber().isAdditiveNumber("19910011992"));
    }
}
