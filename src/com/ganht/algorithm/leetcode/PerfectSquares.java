package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haitian.gan
 */
public class PerfectSquares {

    /** 缓存记录已经算过的值 **/
    private Map<Integer,Integer> cache = new HashMap<>();
    private int minResult = Integer.MAX_VALUE;
    private int target;

    public int numSquares(int n) {
        target = n;
        return numSquares1(n);
    }

    /**
     * 考察的是个狗鸡巴四平方和定理，什么玩意儿
     * @param n
     * @return
     */
    public int numSquares1(int n) {
        if (n == 0) {
            return 0;
        }

        int tmpMinResult = Integer.MAX_VALUE;
        int nearestSqrt = (int) Math.sqrt(n);
        while (nearestSqrt > 0) {
            System.out.println("n:" + n + ",nearestSqrt:" + nearestSqrt);
            int nearestTarget = nearestSqrt * nearestSqrt;
            if (nearestTarget == n) {
                return 1;
            } else {
                int rest = n -nearestTarget;
                Integer existResult = cache.get(rest);
                int left;
                if(existResult != null){
                    left = existResult;
                }else{
                    left = numSquares1(rest);
                    if(left != 0){
                        cache.put(rest, left);
                    }
                }

                if (left == 0) {
                    nearestSqrt = nearestSqrt - 1;
                    continue;
                }

                int tmpResult = 1 + left;
                if(n != target && tmpResult > minResult){
                    nearestSqrt = nearestSqrt - 1;
                    continue;
                }

                if(tmpResult < tmpMinResult){
                    tmpMinResult = tmpResult;

                    if(n == target){
                        minResult = tmpMinResult;
                    }
                }
            }

            nearestSqrt --;
        }

        if(tmpMinResult == Integer.MAX_VALUE){
            return 0;
        }

        return tmpMinResult;
    }

    public static void main(String[] args) {
        System.out.println(new PerfectSquares().numSquares(200));
    }

}
