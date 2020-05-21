package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So
 * the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 *
 * Follow up:
 *
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * @author haitian.gan
 */
public class FindMedianFromDataStream {

    // 思路仍然是边插边排序
    class MedianFinder {

        private TreeSet<Integer> set = new TreeSet<>();
        private Integer lower;
        private Integer higher;

        /** initialize your data structure here. */
        public MedianFinder() {

        }

        public void addNum(int num) {
            set.add(num);
            if (lower == null && higher == null) {
                lower  = num;
                higher = num;
                return;
            }

            if(lower.equals(higher)){
                if(num > lower){
                    higher = set.higher(higher);
                }else{
                    lower = set.lower(lower);
                }
            }else{
                if(num > lower && num > higher){
                    lower = higher;
                }else if(num < lower && num < higher){
                    higher = lower;
                }else{
                    lower = set.higher(lower);
                    higher = lower;
                }
            }
        }

        public double findMedian() {
            return (lower + higher) / 2d;
        }
    }

    public static void main(String[] args){
        FindMedianFromDataStream a = new FindMedianFromDataStream();
        FindMedianFromDataStream.MedianFinder f = a.new MedianFinder();
        f.addNum(0);
        f.addNum(0);
        f.findMedian();
    }



}
