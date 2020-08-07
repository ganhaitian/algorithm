package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and
 * only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example 1:
 *
 * Input: "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: "cbacdcbc"
 * Output: "acdb"
 * Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 * @author haitian.gan
 */
public class RemoveDuplicateLetters {

    private static class Flag{
        int index;
        int flag;
    }

    // 唉，最重要的就是要需要一个辅助的结构，来存储某个字母出现的次数
    // 然后，一个栈就搞定了
    public String removeDuplicateLetters(String s) {
        TreeMap<Character, Flag> cache = new TreeMap<>();
        Character[]              chars = new Character[s.length()];

        char tmp;
        Flag flag;
        for (int i = 0; i < s.length(); i++) {
            tmp = s.charAt(i);
            Flag oldFlag = cache.get(tmp);
            //Flag oldFlag = cache.put(tmp, flag);

            boolean updatePos = false;
            if (oldFlag == null) {
                flag       = new Flag();
                flag.index = i;
                cache.put(tmp, flag);
                chars[i] = tmp;
                updatePos = true;
            } else if (oldFlag.flag == 1) {
                chars[oldFlag.index] = null;
                oldFlag.flag         = 0;
                oldFlag.index        = i;
                chars[i]             = tmp;
                updatePos            = true;
            }

            // 每次更新位置后，要重置标志
            if(updatePos){
                char curr = tmp;
                while (true) {
                    Map.Entry<Character, Flag> higherEntry = cache.higherEntry(curr);
                    if (higherEntry == null) {
                        break;
                    }

                    // 置上标志
                    higherEntry.getValue().flag = 1;
                    curr                        = higherEntry.getKey();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : chars) {
            if (c != null) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    // 用贪婪算法也挺妙的
    public String removeDuplicateLetters1(String s) {
        Stack<Character> stack = new Stack<>();

        // this lets us keep track of what's in our solution in O(1) time
        HashSet<Character> seen = new HashSet<>();

        // this will let us know if there are any more instances of s[i] left in s
        HashMap<Character, Integer> last_occurrence = new HashMap<>();
        for(int i = 0; i < s.length(); i++) last_occurrence.put(s.charAt(i), i);

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            // we can only try to add c if it's not already in our solution
            // this is to maintain only one of each character
            if (!seen.contains(c)){
                // if the last letter in our solution:
                //     1. exists
                //     2. is greater than c so removing it will make the string smaller
                //     3. it's not the last occurrence
                // we remove it from the solution to keep the solution optimal
                while(!stack.isEmpty() && c < stack.peek() && last_occurrence.get(stack.peek()) > i){
                    seen.remove(stack.pop());
                }
                seen.add(c);
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder(stack.size());
        for (Character c : stack) sb.append(c.charValue());
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("cbacdcbc"));
    }

}
