package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.introduction.LinkedLists;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this
 * string is valid. We define the validity of a string by these rules:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * An empty string is also valid.
 * Example 1:
 * Input: "()"
 * Output: True
 * Example 2:
 * Input: "(*)"
 * Output: True
 * Example 3:
 * Input: "(*))"
 * Output: True
 * Note:
 * The string size will be in the range [1, 100].
 */
public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        return doCheck1(s, 0);
    }

    private Map<String, Boolean> resultCache = new HashMap<>();
    private static String[] option = {"(",")",""};

    private boolean doCheck1(String str, int flag){
        String key = str + ":" + flag;
        Boolean result = resultCache.get(key);
        if(result != null){
            return result;
        }

        char c;
        for(int i = 0;i < str.length();i++){
            c = str.charAt(i);
            if(c == '('){
                flag ++;
            }else if(c == ')'){
                if(flag <= 0){
                    return false;
                }else{
                    flag--;
                }
            }else if(c == '*'){
                for(String op : option){
                    String s = op + ((i + 1) < str.length() ? str.substring(i + 1) : "");
                    if(doCheck1(s, flag)){
                        return true;
                    }
                }
            }
        }

        result = flag <= 0;
        resultCache.put(key, result);
        return result;
    }

    // 这个傻逼了，惯性思维看到这种符号校验就用了栈，其实如果只有一种符号，一个标志位就够了
    private boolean doCheck(String str, LinkedList<Character> stack){
        char c;
        for(int i = 0;i < str.length();i++){
            c = str.charAt(i);
            if(c == '('){
                stack.push(c);
            }else if(c == ')'){
                if(stack.isEmpty()){
                    return false;
                }else{
                    stack.pop();
                }
            }else if(c == '*'){
                for(String op : option){
                    String s = op + ((i + 1) < str.length() ? str.substring(i + 1) : "");
                    if(doCheck(s, new LinkedList<>(stack))){
                        return true;
                    }
                }
            }
        }

        return stack.size() <= 0;
    }

    public static void main(String[] args){
        System.out.print(new ValidParenthesisString().checkValidString("(*))"));
    }

}
