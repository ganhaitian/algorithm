package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * You are given a char array representing tasks CPU need to do. It contains
 * capital letters A to Z where each letter represents a different task. Tasks
 * could be done without the original order of the array. Each task is done in
 * one unit of time. For each unit of time, the CPU could complete either one
 * task or just be idle.
 * 
 * However, there is a non-negative integer n that represents the cooldown
 * period between two same tasks (the same letter in the array), that is that
 * there must be at least n units of time between any two same tasks.
 * 
 * You need to return the least number of units of times that the CPU will take
 * to finish all the given tasks.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: tasks = ['A','A','A','B','B','B'], n = 2 Output: 8 Explanation: A -> B
 * -> idle -> A -> B -> idle -> A -> B There is at least 2 units of time between
 * any two same tasks. Example 2:
 * 
 * Input: tasks = ['A','A','A','B','B','B'], n = 0 Output: 6 Explanation: On
 * this case any permutation of size 6 would work since n = 0.
 * ['A','A','A','B','B','B'] ['A','B','A','B','A','B'] ['B','B','B','A','A','A']
 * ... And so on. Example 3:
 * 
 * Input: tasks = ['A','B','C','D','E','A','B','C','D','E'], n = 4 Output: 10
 * 
 * 
 * Constraints:
 * 
 * The number of tasks is in the range [1, 10000]. The integer n is in the range
 * [0, 100].
 * 
 * @author ganha
 *
 */
public class TaskScheduler {

	// 因为相同字母之间必须有n这么大个儿空隙，那说明这个长度肯定是由出现次数最多的那个字母决定的
	// 第一个例子举的特别好，基本能涵盖一半的情况：A -> B -> idle -> A -> B -> idle -> A -> B
	// A出现次数最多，是3次，所以A中间的空隙总共有 (3 - 1) * 2 = 4个，而B也有3个，依次在空隙里面摆上B
	// 到最后冒出去了，这样最大长度就是8，所以最大长度是由出现次数最多，并且是同样多的字母个数决定的。
	// 剩下就是其它字母往空隙里面摆
	public int leastInterval(char[] tasks, int n) {
		var map = new HashMap<Character, Integer>();
		var max = 0;
		for (int i = 0; i < tasks.length; i++) {
			int val = map.compute(tasks[i], (k, v) -> v == null ? 1 : v + 1);
			if (val > max) {
				max = val;
			}
		}

		int num = 0;
		for (int times : map.values()) {
			if (times == max) {
				num++;
			}
		}

		var maxLength = (max - 1) * (n + 1) + num;
		return Math.max(maxLength, tasks.length);
	}

	// LeetCode上面的答案，看着更简洁一点？
	public int leastInterval1(char[] tasks, int n) {
		int[] map = new int[26];
		for (char c : tasks)
			map[c - 'A']++;
		Arrays.sort(map);
		int max_val = map[25] - 1, idle_slots = max_val * n;
		for (int i = 24; i >= 0 && map[i] > 0; i--) {
			idle_slots -= Math.min(map[i], max_val);
		}
		return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
	}

	public static void main(String[] args) {
		char[] input = { 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		new TaskScheduler().leastInterval(input, 2);
	}

}
