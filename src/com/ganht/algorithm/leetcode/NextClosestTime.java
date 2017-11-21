package com.ganht.algorithm.leetcode;


import java.util.ArrayList;
import java.util.List;

public class NextClosestTime {

    public String nextClosestTime(String time) {
        String number = time.replace(":","");
        List<String> numberList = new ArrayList<>();

        for(char c : number.toCharArray())
            numberList.add(Character.toString(c));

        String closestTime = buildTimeStr("",numberList,time);
        return closestTime.substring(0,2) + ":" + closestTime.substring(2,4);
    }

    public static void main(String[] args){
        new NextClosestTime().nextClosestTime("19:34");
    }

    private String buildTimeStr(String timeStr,List<String> numbers,String inputTime){
        if(timeStr.length() >= 4)
            return timeStr;

        String closestTime = "";
        int minDiff = 0;
        for(String number : numbers){
            //String newStr = timeStr + buildTimeStr(timeStr,numbers,inputTime);
            String newStr = timeStr + number;
            if(isValidTime(newStr)){
                if(closestTime.equals("")) {
                    closestTime = newStr;
                    minDiff = calTimeDistance(newStr,inputTime);
                }
                else{
                    int diff = calTimeDistance(newStr,inputTime);
                    if(diff < minDiff){
                        minDiff = diff;
                        closestTime = newStr;
                    }
                }
            }else{
                newStr = buildTimeStr(newStr,numbers,inputTime);
            }
        }

        return closestTime;
    }

    private int calTimeDistance(String time1Str,String time2Str){
        int time1 = Integer.parseInt(time1Str);
        int time2 = Integer.parseInt(time2Str);

        int hour1 = time1 / 100;
        int hour2 = time2 / 100;
        int min1 = time1 % 100;
        int min2 = time2 % 100;

        int distance1 = Math.abs(hour1 - hour2 * 60 + min1 - min2);

        if(hour1 == 0)
            hour1 = 24;
        if(hour2 == 0)
            hour2 = 24;

        int distance2 = Math.abs(hour1 - hour2 * 60 + min1 - min2);
        return Math.min(hour1,hour2);
    }

    private boolean isValidTime(String timeStr){
        int timeNum = Integer.parseInt(timeStr);
        int hour = timeNum / 100;
        int min = timeNum % 100;

        if(hour > 23)
            return false;

        if(min > 59)
            return false;

        return true;
    }

}
