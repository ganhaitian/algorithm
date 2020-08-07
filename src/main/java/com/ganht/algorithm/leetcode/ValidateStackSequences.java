package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result
 * of a sequence of push and pop operations on an initially empty stack.
 *
 *
 *
 * Example 1:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * Example 2:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * Output: false
 * Explanation: 1 cannot be popped before 2.
 *
 *
 * Note:
 *
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed is a permutation of popped.
 * pushed and popped have distinct values.
 * @author haitian.gan
 */
public class ValidateStackSequences {

    // 贪婪算法，挺精妙的
    public boolean validateStackSequences1(int[] pushed, int[] popped) {
        int            N     = pushed.length;
        Stack<Integer> stack = new Stack();

        int j = 0;
        for (int x: pushed) {
            stack.push(x);
            while (!stack.isEmpty() && j < N && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return j == N;
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length == 0 || popped.length == 0){
            return true;
        }

        Map<Integer, Integer> posMap = new HashMap<>();
        for (int i = 0; i < pushed.length; i++) {
            posMap.put(pushed[i], i);
        }

        Set<Integer> poppedSet = new HashSet<>();
        poppedSet.add(popped[0]);

        int prevPos    = posMap.get(popped[0]);
        int nextPopPos = prevPos - 1;
        for (int j = 1; j < popped.length; j++) {
            int ele     = popped[j];
            int currPos = posMap.get(ele);
            if (currPos < prevPos) {
                if (posMap.get(ele) != nextPopPos) {
                    return false;
                }
            }

            prevPos = currPos;
            poppedSet.add(ele);
            while (currPos >= 0 && poppedSet.contains(pushed[currPos--])) {
                nextPopPos = currPos;
            }

        }

        return true;
    }

    public static void main(String[] args) {
        int[] input1 = {1, 2, 3, 4, 5};
        int[] input2 = {4, 3, 5, 1, 2};
        System.out.println(new ValidateStackSequences().validateStackSequences(input1, input2));
    }

}
