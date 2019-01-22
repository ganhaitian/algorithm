package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author haitian.gan
 */
public class MergeIntervals {

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        // 先排序
        intervals.sort(Comparator.comparingInt(i -> i.start));

        Interval mergeInterval = null;
        List<Interval> result = new ArrayList<>();
        for (Interval interval : intervals) {
            if (mergeInterval == null) {
                mergeInterval = interval;
                continue;
            }

            if (mergeInterval.end >= interval.start) {
                mergeInterval = new Interval(mergeInterval.start, Math.max(mergeInterval.end, interval.end));
            } else {
                result.add(mergeInterval);
                mergeInterval = interval;
            }
        }

        if (mergeInterval != null) {
            result.add(mergeInterval);
        }

        return result;
    }

    public static void main(String[] args){
        Interval i1 = new Interval(11,13);
        Interval i2 = new Interval(1,4);
        Interval i3 = new Interval(17,18);
        Interval i4 = new Interval(2,9);

        List<Interval> inputList = Arrays.asList(i1,i2,i3,i4);
        List<Interval> result = new MergeIntervals().merge(inputList);
    }

}
