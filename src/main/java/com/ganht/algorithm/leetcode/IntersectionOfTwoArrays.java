package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Note:
 *
 * Each element in the result must be unique.
 * The result can be in any order.
 * @author haitian.gan
 */
public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> unique1 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            unique1.add(nums1[i]);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if(unique1.contains(nums2[i])){
                result.add(nums2[i]);
                unique1.remove(nums2[i]);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

}
