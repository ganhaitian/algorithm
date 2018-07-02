package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *   There are two sorted arrays nums1 and nums2 of size m and n respectively.

     Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

     Example 1:
     nums1 = [1, 3]
     nums2 = [2]

     The median is 2.0
     Example 2:
     nums1 = [1, 2]
     nums2 = [3, 4]

     The median is (2 + 3)/2 = 2.5
 *
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays1(int[] nums1,int[] nums2){
        return 0;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> numList = new ArrayList<>();
        Arrays.stream(nums1).forEach(numList::add);
        Arrays.stream(nums2).forEach(numList::add);

        Collections.sort(numList);
        int size = numList.size();

        if(size == 0)
            return 0;

        if(size % 2 == 0){
            return (numList.get(size / 2 - 1) + numList.get(size / 2)) / 2d;
        }else{
            return numList.get(size / 2);
        }
    }

    public static void main(String[] args){
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1,nums2));
    }

}
