package com.ganht.algorithm.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 * Note:
 *
 * S will consist of lowercase letters and have length in range [1, 500].
 * @author haitian.gan
 */
public class ReorganizeString {

    // 没写出来具体的，只是有思路
    public String reorganizeString(String S) {
        Set<String>          letters   = new HashSet<>();
        Map<String, Integer> letterMap = new HashMap<>();
        String               letter;
        for (char a : S.toCharArray()) {
            letter = String.valueOf(a);
            letterMap.compute(letter, (k, v) -> v == null ? 1 : v + 1);
            letters.add(letter);
        }

        List<String> letterList = new ArrayList<>(letters);
        letterList.sort(Comparator.comparing(letterMap::get));

        StringBuilder resultBuilder  = new StringBuilder();
        String        s              = String.join("", letters);
        boolean       first          = true;
        int lastLetterTime = 0;
        for (String l : letterList) {
            if (!first) {
                s = s.replace(l, "");
            } else {
                first = false;
            }

            int repeatTimes = letterMap.get(l);
            for (int i = 0; i < repeatTimes - lastLetterTime; i++) {
                resultBuilder.append(s);
            }

            lastLetterTime = repeatTimes;
        }

        return resultBuilder.toString();
    }

}
