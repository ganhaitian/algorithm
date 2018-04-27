package com.ganht.algorithm.leetcode;

import java.util.LinkedList;

public class ValidParentheses {

    public boolean isValid(String s) {
        if(s == null || s.length() == 0)
            return true;

        LinkedList<Character> stack = new LinkedList<>();
        for(char c : s.toCharArray()){
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            }else{
                Character top = stack.pollFirst();
                if(top == null)
                    return false;

                if(c == ')' && top != '('){
                    return false;
                }else if(c == '}' && top != '{'){
                    return false;
                }
                else if( c == ']' && top !='['){
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }

    public static void main(String[] args){
        System.out.println(new ValidParentheses().isValid("{[]}"));
    }

}
