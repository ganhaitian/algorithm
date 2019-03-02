package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * @author haitian.gan
 */
public class MeetingRoomsII {

    public class Interval {
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

    private List<Interval> rangeList = new ArrayList<>();

    /*public int minMeetingRooms(Interval[] intervals) {
        for(Interval interval : intervals){
            for(Interval range : rangeList){
                if(interval.start <= range.start && interval.end >= range.start){
                    range.start = interval.start;
                }else if(interval.start >= range.start && interval.end <= range.end){

                }else if(interval.end >= range.end && interval.start <= range.end){
                    range.end = interval.end;
                }
            }
        }
    }*/

    /**
     * 使用优先队列，这个问题的关键点在于，要按照顺序把interval排好
     * 遍历的时候，要把前面已经开完会，结束占用的会议室空出来，重新占上
     * 记住，当需要一个频繁加入，移除还能保证顺序的数据结构的时候，要想到堆
     * 在java里面就是优先队列
     * @param intervals
     * @return
     */
    public int minMeetingRooms(Interval[] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        // Min heap
        PriorityQueue<Integer> allocator = new PriorityQueue<>(intervals.length, Comparator.comparingInt(a -> a));
        // Sort the intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
        // Add the first meeting
        allocator.add(intervals[0].end);

        // Iterate over remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (intervals[i].start >= allocator.peek()) {
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i].end);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }

    public static void main(String[] args){

    }

}
