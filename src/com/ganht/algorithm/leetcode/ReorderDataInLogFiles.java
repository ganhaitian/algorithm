package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You have an array of logs.  Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one
 * word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically
 * ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 *
 *
 * Example 1:
 *
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *
 *
 * Constraints:
 *
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 * @author haitian.gan
 */
public class ReorderDataInLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        Map<String, Integer> digitLogMap  = new HashMap<>();
        Map<String, Integer> letterLogMap = new HashMap<>();

        String   log, wordAfterId;
        char     _1stWord;
        String[] parts;
        for (int i = 0; i < logs.length; i++) {
            log = logs[i];

            parts = log.split(" ");
            wordAfterId = parts[1];
            _1stWord = wordAfterId.charAt(0);
            if (_1stWord >= '0' && _1stWord <= '9') {
                digitLogMap.put(parts[0] + wordAfterId, i);
            } else {
                letterLogMap.put(parts[0] + wordAfterId, 0);
            }
        }

        Arrays.sort(logs, (l1, l2) -> {
            String i1Id  = l1.split(" ")[0];
            String i1Key = i1Id + l1.split(" ")[1];
            String i2Id  = l2.split(" ")[0];
            String i2Key = i2Id + l2.split(" ")[1];

            if (letterLogMap.containsKey(i1Key)) {
                if (digitLogMap.containsKey(i2Key)) {
                    return -1;
                } else {
                    int cmp = l1.substring(i1Id.length() + 1).compareTo(l2.substring(i2Id.length() + 1));
                    if(cmp == 0){
                        cmp = i1Id.compareTo(i2Id);
                    }

                    return cmp;
                }
            } else {
                if (digitLogMap.containsKey(i2Key)) {
                    return Integer.compare(digitLogMap.get(i1Key), digitLogMap.get(i2Key));
                } else {
                    return 1;
                }
            }
        });

        return logs;
    }

    public static void main(String[] args){
        String[] logs = {"t kvr", "r 3 1", "i 403", "7 so", "t 54"};
        logs = new ReorderDataInLogFiles().reorderLogFiles(logs);
        System.out.println(logs);
    }

}
