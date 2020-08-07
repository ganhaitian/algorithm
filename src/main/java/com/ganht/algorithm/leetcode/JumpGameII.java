package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * Example:
 * 
 * Input: [2,3,1,1,4] Output: 2 Explanation: The minimum number of jumps to
 * reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the
 * last index. Note:
 * 
 * You can assume that you can always reach the last index.
 * 
 * @author jia
 *
 */
public class JumpGameII {
	
	private Map<Integer,Integer> minCache = new HashMap<>();

	public int jump(int[] nums) {
		return jump(nums, 0);
	}

	// 想得挺好，思路也算流畅，但是超时了，想想贪婪......
	// 贪婪好巧妙呀
	private int jump(int[] nums, int startIndex) {
		Integer cacheMinVal = minCache.get(startIndex);
		if(cacheMinVal != null) {
			return cacheMinVal;
		}
		
		if (startIndex == nums.length - 1) {
			return 0;
		}
		
		if(startIndex > nums.length - 1 || nums[startIndex] == 0) {
			return -1;
		}
		
		int min = Integer.MAX_VALUE;
		for (int step = 1; step <= nums[startIndex]; step++) {
			int forwardStep = jump(nums, startIndex + step);
			if(forwardStep == -1) {
				continue;
			}
			
			int steps = jump(nums, startIndex + step) + 1;
			if (steps < min) {
				min = steps;
			}
		}
		
		if(min == Integer.MAX_VALUE) {
			return -1;
		}

		minCache.put(startIndex, min);
		return min;
	}

	public static void main(String[] args) {
		int[] nums = {6,2,6,1,7,9,3,5,3,7,2,8,9,4,7,7,2,2,8,4,6,6,1,3};
		int result = new JumpGameII().jump(nums);
		System.out.println(result);
	 }

}
