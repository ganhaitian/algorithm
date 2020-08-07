package com.ganht.algorithm.leetcode;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * @author haitian.gan
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        int i = 0,j = nums.length - 1,mid = 0;
        while(i <= j){
            mid = (i + j) / 2;
            if(nums[mid] == target){
                return mid;
            }

            if(nums[i] > nums[mid]){
                if(target >= nums[mid] && target <= nums[j]){
                    i = mid + 1;
                }else{
                    j = mid - 1;
                }
            }else if(nums[mid] > nums[j]){
                if(target >= nums[i] && target <= nums[mid]){
                    j = mid - 1;
                }else{
                    i = mid + 1;
                }
            }else{
                if(target >= nums[i] && target <= nums[mid]){
                    j = mid - 1;
                }else{
                    i = mid + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args){
        int[] nums = {4,5,6,7,0,1,2};
        new SearchInRotatedSortedArray().search(nums, 3);
    }

}
