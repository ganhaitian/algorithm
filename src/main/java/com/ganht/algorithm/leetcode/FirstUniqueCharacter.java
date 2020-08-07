package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

     Examples:

     s = "leetcode"
     return 0.

     s = "loveleetcode",
     return 2.
     Note: You may assume the string contain only lowercase letters.

     Subscribe to see which companies asked this question

 * Created by 17ZY-HPYKFD2 on 2016/11/3.
 */
public class FirstUniqueCharacter {

    public int firstUniqChar(String s) {

        if(s == null || s.length() == 0)
            return -1;

        int[] buff = new int[127];
        List<Character> chars = new ArrayList<>();

        for(char c : s.toCharArray()){
            buff[c] ++;
            if(!chars.contains(c))
                chars.add(c);
        }

        for(char c : chars){
            if(buff[c] == 1)
                return s.indexOf(c);
        }

        return -1;
    }

    public static void main(String[] args){
        new FirstUniqueCharacter().firstUniqChar("loveleetcode");
    }
}

