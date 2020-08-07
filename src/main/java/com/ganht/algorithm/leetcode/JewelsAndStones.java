package com.ganht.algorithm.leetcode;

/**
 * @author haitian.gan
 */
public class JewelsAndStones {

    public int numJewelsInStones(String J, String S) {
        int[] cache = new int[256];
        for(int i = 0;i < J.length();i++){
            cache[J.charAt(i)] = 1;
        }

        int result = 0;
        for (int j = 0; j < S.length(); j++) {
            if(cache[S.charAt(j)] == 1){
                result ++;
            }
        }

        return result;
    }

}
