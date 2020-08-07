package com.ganht.algorithm.leetcode;



import java.util.*;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 * Created by lenovo on 2016/7/13.
 */
public class RemoveInvalidParentheses {

    // 二刷，感觉这个方案比leetcode上面给的要清晰明了
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();

        Queue<String> q = new LinkedList<String>();
        q.add(s);

        Map<String,Integer> visited = new HashMap<String,Integer>();
        Queue<String> next = new LinkedList<String>();

        while(q.size() > 0){
            String str = q.poll();
            if(isValid(str)){
                result.add(str);
            }
            else{
                for(int i =0;i < str.length();i++){
                    if(str.charAt(i) != '(' && str.charAt(i) != ')')
                        continue;

                    String newStr = str.substring(0,i) + str.substring(i + 1);
                    if(!visited.containsKey(newStr)){
                        visited.put(newStr,1);
                        next.add(newStr);
                    }

                }
            }

            if(q.size() == 0){
                if(result.size() > 0)
                    break;

                q.clear();
                q.addAll(next);

                next.clear();
            }
        }

        if(result.size() == 0)
            result.add("");

        return result;
    }

    private boolean isValid(String s){
        int sum = 0;
        for(int i =0;i < s.length();i++){
            if(s.charAt(i) == '(')
                sum ++;
            else if(s.charAt(i) == ')' && sum -- == 0)
                return false;
        }

        return sum == 0;
    }

    public static void main(String[] args){
        System.out.println(new RemoveInvalidParentheses().removeInvalidParentheses(")("));
    }

}
