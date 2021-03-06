package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Share
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets
 * in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * @author haitian.gan
 */
public class _3Sum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;

        int count = 0;
        List<List<Integer>> result = new ArrayList<>();
        while (j > i) {
            int sub = -(nums[i] + nums[j]);
            // 鍙互鍦ㄥ尯闂撮噷闈㈡悳绱�
            if (sub <= nums[j] && sub >= nums[i]) {
                int findIndex = Arrays.binarySearch(nums, i + 1, j - 1, sub);
                if (findIndex >= 0) {
                    result.add(Arrays.asList(nums[i], nums[findIndex], nums[j]));
                }

                count ++;
            } else if (sub > nums[j]) {	
                i++;
            } else if (sub < nums[i]) {
                j--;
            }

            if (count % 2 == 0) {
                i ++;
            }else{
                j --;
            }
        }

        return result;
    }
    
    private static int calculate1(int D, int[] buildings){
        int i = 0;
        int j = buildings.length - 1;
        while(i < buildings.length - 2) {
        	if(buildings[j] - buildings[i] > D) {
        		j--;
        	}else {
        		
        	}
        }
        
        return 0;
    } 
    
    private static int calculate(int D, int[] buildings, int start, int level){
        // System.out.println(D + " " + start + " " + level);
        if(start >= buildings.length){
            return 0;
        }
        
        if(level == 0){
            return 1;
        }
        
        int sum = 0;
        for(int j = start;j < buildings.length;j++){
            for(int i = j + 1;i < buildings.length;i++){
                if(buildings[i] - buildings[j] < D){
                    sum += calculate(D, buildings, i, level - 1);
                }else{
                    break;
                }
            }
        }
        
        return sum;
    } 
    
    private static void sln(int[] locs, int D) {
    	long cnt = 0;
        int left = 0, right = 2;
        while (right < locs.length) {
            if (locs[right] - locs[left] > D) left++;
            else if (right - left < 2) right++;
            else {
                cnt += calC(right - left);
                right++;
            }
        }
        cnt %= 99997867;
        System.out.println(cnt);
    }
  
    private static long calC(long num) {
        return num * (num - 1) / 2;
    }

    public static void main(String[] args){
		/*
		 * int[] input = {-1, 0, 1, 2, -1, -4}; new _3Sum().threeSum(input);
		 */
    	
    	int[] input = {1,2,3,4};
    	sln(input, 3);
    }

}
