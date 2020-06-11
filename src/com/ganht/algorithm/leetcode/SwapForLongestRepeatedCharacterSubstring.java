package com.ganht.algorithm.leetcode;

/**
 * Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring
 * with repeated characters.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "ababa"
 * Output: 3
 * Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated
 * character substring is "aaa", which its length is 3.
 * Example 2:
 *
 * Input: text = "aaabaaa"
 * Output: 6
 * Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa",
 * which its length is 6.
 * Example 3:
 *
 * Input: text = "aaabbaaa"
 * Output: 4
 * Example 4:
 *
 * Input: text = "aaaaa"
 * Output: 5
 * Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
 * Example 5:
 *
 * Input: text = "abcdef"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 20000
 * text consist of lowercase English characters only.
 * @author haitian.gan
 */
public class SwapForLongestRepeatedCharacterSubstring {

    // 本来挺简单的，自己给想复杂了，需要变化并借助一个辅助的数组
    public int maxRepOpt1(String text) {
        Character repeatChar    = null;
        Character maxRepeatChar = null;
        int       repeatNum     = 0;
        int       diffNum       = 0;
        int       maxRepeat     = 0;
        int       lastDifIndex  = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (repeatChar == null) {
                repeatChar = c;
                repeatNum  = 1;
                diffNum    = 0;
            } else {
                if (c != repeatChar) {
                    diffNum++;
                    if (diffNum >= 2) {
                        if (i - lastDifIndex <= 2) {
                            i = lastDifIndex - 1;
                        } else {
                            i = i - 1;
                        }

                        repeatChar = null;

                        if (repeatNum > maxRepeat) {
                            maxRepeat     = repeatNum;
                            maxRepeatChar = repeatChar;
                        }
                    }else{
                        lastDifIndex = i;
                    }
                } else {
                    repeatNum++;
                }
            }
        }

        return maxRepeat;
    }

    public static void main(String[] args){
        new SwapForLongestRepeatedCharacterSubstring().maxRepOpt1("ababa");
    }

}
