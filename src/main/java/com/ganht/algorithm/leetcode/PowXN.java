package com.ganht.algorithm.leetcode;

public class PowXN {

    public double myPow(double x, int n) {
        /*double result = 1d;
        else{
            for(int i = 1;i <= Math.abs(n);i++){
                result = result * x;
            }
        }

        if(n < 0) result = 1d / result;
        return result;*/

        if(n == 0)
            return 1.0d;

        double z= x;
        long tmp = 2;
        for(; tmp <= Math.abs(n); ){
            tmp = tmp * 2;

            z = z * z;
        }

        int near = (int)(tmp / 2);
        double left = myPow(x,Math.abs(n) - near);
        z = z * left;

        if(n < 0) z = 1d/ z;
        return z;
    }

    public static void main(String[] args){
        System.out.println(new PowXN().myPow(0.00001,2147483647));
    }

}
