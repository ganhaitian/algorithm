package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for
 * $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means
 * person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the
 * person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
 *
 * Given a list of transactions between a group of people, return the minimum number of transactions required to settle
 * the debt.
 *
 * Note:
 *
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 * Example 1:
 *
 * Input:
 * [[0,1,10], [2,0,5]]
 *
 * Output:
 * 2
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 *
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * Example 2:
 *
 * Input:
 * [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 *
 * Output:
 * 1
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 *
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 * @author haitian.gan
 */
public class OptimalAccountBalancing {

    // 第一步要想到把所有操作都合并
    // 第二步要想到正负账户余额相抵消就是在模拟一次转账操作
    public int minTransfers(int[][] transactions) {
        Map<Integer, Long> map = new HashMap<>();
        for (int[] transaction : transactions) {
            long balance1 = map.getOrDefault(transaction[0], 0L);
            long balance2 = map.getOrDefault(transaction[1], 0L);
            map.put(transaction[0], balance1 - transaction[2]);
            map.put(transaction[1], balance2 + transaction[2]);
        }

        List<Long> list = new ArrayList<>();
        for (long val : map.values()) {
            if (val != 0) {
                list.add(val);
            }
        }
        int matchCount = removeMatch(list);
        return matchCount + helper(list, 0);
    }
    //pre-process to decrease dfs processing time, this step can be optional.
    private int removeMatch(List<Long> list) {
        Collections.sort(list);
        int left = 0, right = list.size() - 1;
        int matchCount = 0;
        while (left < right) {
            if (list.get(left) + list.get(right) == 0) {
                list.remove(left);
                list.remove(right - 1);
                right -= 2;
                matchCount++;
            } else if (list.get(left) + list.get(right) < 0) {
                left++;
            } else {
                right--;
            }
        }
        return matchCount;
    }

    private int helper(List<Long> list, int start) {
        int result = Integer.MAX_VALUE;
        int n = list.size();
        while (start < n && list.get(start) == 0) {
            start++;
        }
        if (start == n) {
            return 0;
        }
        //这个for loop来处理dfs特别的巧，他把整个数组放在下一次递归里，并没有重新创建一个sublist，会节约空间，但是面试的时候，
        // 如果面试官不知道整个方法，可能会比较麻烦，因为这种方法不是那么容易理解。
        for (int i = start + 1; i < n; i++) {
            if (list.get(i) * list.get(start) < 0) {
                list.set(i, list.get(i) + list.get(start));
                result = Math.min(result, 1 + helper(list, start + 1));
                list.set(i, list.get(i) - list.get(start));
            }
        }
        return result;
    }

}
