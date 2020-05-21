package com.ganht.algorithm.leetcode;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from
 * nums2.
 * Example:
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 * @author haitian.gan
 */
public class MergeSortedArray {

        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if(n == 0){
                return;
            }else if(m == 0){
                System.arraycopy(nums2, 0, nums1, 0, n);
            }

            int i = m - 1,j = n - 1, p = nums1.length - 1;
            while(j >= 0){
                if(i == -1 || nums2[j] >= nums1[i]){
                    nums1[p--] = nums2[j--];
                }else{
                    nums1[p--] = nums1[i];
                    nums1[i--] = 0;
                }
            }
        }

    public static void main(String[] args){

    }

}
