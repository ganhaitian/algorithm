package com.ganht.algorithm.leetcode;

import com.sun.org.apache.xerces.internal.impl.dtd.DTDGrammarBucket;
import javafx.util.Pair;

import java.util.*;

/**
 * Given an unsorted array, find the maximum difference between the
 * successive elements in its sorted form.
 * <p/>
 * Try to solve it in linear time/space.
 * <p/>
 * Return 0 if the array contains less than 2 elements.
 * <p/>
 * You may assume all elements in the array are non-negative integers
 * and fit in the 32-bit signed integer range
 */
public class MaxiumGap {

    // 解决思路运用的是桶排序
    public int maximumGap(int[] nums) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // 滤重
        Set<Integer> numSet = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min)
                min = nums[i];
            if (nums[i] >= max)
                max = nums[i];

            numSet.add(nums[i]);
        }

        int averageGap = (int)Math.ceil((double)(max - min) / (numSet.size() - 1));
        int bucketNums = (int)Math.ceil((double)(max - min) / averageGap);

        List<int[]> buckets = new ArrayList<int[]>();
        for(int i =0;i < bucketNums;i++)
            buckets.add(new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE});

        for (int num : numSet) {
            if (num == min || num == max)
                continue;

            int bucketIndex = (num - min) / averageGap;

            int[] bucketData = buckets.get(bucketIndex);
            if (num <= bucketData[0])
                bucketData[0] = num;
            if (num >= bucketData[1])
                bucketData[1] = num;
        }

        int lastMax = min;
        int maxGap = 0;
        for (int[] bucketData : buckets) {
            if (bucketData[0] == Integer.MAX_VALUE)
                continue;

            maxGap = Math.max(bucketData[0] - lastMax, maxGap);
            lastMax = bucketData[1];
        }

        maxGap = Math.max(maxGap,max- lastMax);
        System.out.println(maxGap);
        return maxGap;
    }

    public static void main(String[] args) {
        new MaxiumGap().maximumGap(new int[]{1,1,1,1,1,5,5,5,5,5});
    }

}
