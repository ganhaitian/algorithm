package com.ganht.algorithm.leetcode;


import java.util.HashSet;
import java.util.Set;

/**
 *   Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
 *   There is no limit on how many times a digit can be reused.

     You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

     Example 1:

     Input: "19:34"
     Output: "19:39"
     Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.It is not 19:33,
        because this occurs 23 hours and 59 minutes later.

     Example 2:

     Input: "23:59"
     Output: "22:22"
     Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time i
 */
public class NextClosestTime {

    public String nextClosestTime(String time) {
        String number = time.replace(":", "");
        Set<String> numberList = new HashSet<>();

        for (char c : number.toCharArray())
            numberList.add(Character.toString(c));

        String closestTime = buildTimeStr("", numberList, number);
        return closestTime.substring(0, 2) + ":" + closestTime.substring(2, 4);
    }

    /**
     * 现在就是递归，总共可能的数字有几种，每一位轮番去换数字，拼出个结果。然后和目标去计算差的时间有多少，选最小的那个
     * 网站给的答案思路要巧妙一点，他是模拟时钟一分一分往后走，然后看时间点里面各个位的数字是不是在选定范围里面的。至到找到最近的。
     * @param timeStr
     * @param numbers
     * @param inputTime
     * @return
     */
    private String buildTimeStr(String timeStr, Set<String> numbers, String inputTime) {
        if (timeStr.length() >= 4)
            return timeStr;

        String closestTime = inputTime;
        int minDiff = 0;
        for (String number : numbers) {
            //String newStr = timeStr + buildTimeStr(timeStr,numbers,inputTime);
            String newStr = timeStr + number;
            if (newStr.length() < 4)
                newStr = buildTimeStr(newStr, numbers, inputTime);

            // 原时间的那个组合跳过去
            if(newStr.equals(inputTime))
                continue;

            if (isValidTime(newStr)) {
                int diff = calTimeDistance(newStr, inputTime);
                if (closestTime.equals(inputTime)) {
                    closestTime = newStr;
                    minDiff = diff;
                } else {
                    if (diff < minDiff) {
                        minDiff = diff;
                        closestTime = newStr;
                    }
                }
            }
        }

        return closestTime;
    }

    private int calTimeDistance(String time1Str, String time2Str) {
        int time1 = Integer.parseInt(time1Str);
        int time2 = Integer.parseInt(time2Str);

        int hour1 = time1 / 100;
        int hour2 = time2 / 100;
        int min1 = time1 % 100;
        int min2 = time2 % 100;

        int distance = (hour1 - hour2) * 60 + min1 - min2;
        if(distance < 0)
            distance = 24 * 60;

        return distance;
    }

    private boolean isValidTime(String timeStr) {
        if(timeStr.length() == 0)
            return false;

        int timeNum = Integer.parseInt(timeStr);
        int hour = timeNum / 100;
        int min = timeNum % 100;

        if (hour > 23)
            return false;

        if (min > 59)
            return false;

        return true;
    }

    public static void main(String[] args) {
        System.out.print(new NextClosestTime().nextClosestTime("23:59"));
    }

}
