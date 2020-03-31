package com.ganht.algorithm.leetcode;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each
 * letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 * @author haitian.gan
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        // 记录某一个字母第一次出现和最后一次出现的位置
        Map<Character, int[]> posMap = new HashMap<>();
        for (int index = 0; index < S.length(); index++) {
            int _index = index;
            posMap.compute(S.charAt(index), (c, pos) -> {
                if (pos == null) {
                    pos = new int[2];
                    pos[0] = -1;
                }

                if (pos[0] == -1) {
                    pos[0] = _index;
                }

                if (_index >= pos[1]) {
                    pos[1] = _index;
                }

                return pos;
            });
        }

        // 按区间的第一次出现位置排序
        List<int[]> rangeList = posMap.values()
                .stream()
                .sorted(Comparator.comparingInt(p -> p[0]))
                .collect(toList());

        // 结果
        List<Integer> result   = new ArrayList<>();

        int[]         tmpRange = null;
        for (int[] range : rangeList) {
            if (tmpRange == null) {
                tmpRange = new int[]{range[0],range[1]};
            } else {
                if (range[0] >= tmpRange[0] && range[0] <= tmpRange[1]) {
                    if (range[1] > tmpRange[1]) {
                        tmpRange[1] = range[1];
                    }
                } else {
                    result.add(tmpRange[1] - tmpRange[0] + 1);
                    tmpRange = new int[]{range[0],range[1]};
                }
            }
        }

        if(tmpRange != null){
            int letterSum = result.stream().mapToInt(a -> a).sum();
            if(letterSum < S.length()){
                result.add(tmpRange[1] - tmpRange[0] + 1);
            }
        }

        return result;
    }

    public static void main(String[] args){
        List<Integer> result = new PartitionLabels().partitionLabels("ababcbacadefegdehijhklij");
        System.out.println(result);
    }

}
