package com.ganht.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting
 * parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * Example 4:
 *
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 * @author haitian.gan
 */
public class MinimumRemoveToMakeValidParentheses {

    // 这是标准的答案，和我的思路差不多，但感觉没有我的简洁
    public String minRemoveToMakeValid1(String s) {
        Set<Integer>   indexesToRemove = new HashSet<>();
        Stack<Integer> stack           = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public String minRemoveToMakeValid(String s) {
        Stack<StringBuilder> stack  = new Stack<>();
        stack.push(new StringBuilder());

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                StringBuilder sb = new StringBuilder();
                stack.push(sb);
            } else if (c == ')') {
                if (stack.size() <= 1) {
                    continue;
                }

                String inner = stack.pop().toString();
                stack.peek().append("(").append(inner).append(")");
            } else {
                stack.peek().append(c);
            }
        }

        while(stack.size() >= 2){
            String inner = stack.pop().toString();
            stack.peek().append(inner);
        }

        return stack.peek().toString();
    }

    public static void main(String[] args){
        System.out.println(new MinimumRemoveToMakeValidParentheses().minRemoveToMakeValid("(a(b(c)d)"));
    }

}
