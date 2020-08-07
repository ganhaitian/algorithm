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

	// ��Ϊ��ͬ��ĸ֮�������n��ô�������϶����˵��������ȿ϶����ɳ��ִ��������Ǹ���ĸ������
	// ��һ�����Ӿٵ��ر�ã������ܺ���һ��������A -> B -> idle -> A -> B -> idle -> A -> B
	// A���ִ�����࣬��3�Σ�����A�м�Ŀ�϶�ܹ��� (3 - 1) * 2 = 4������BҲ��3���������ڿ�϶�������B
	// �����ð��ȥ�ˣ�������󳤶Ⱦ���8��������󳤶����ɳ��ִ�����࣬������ͬ�������ĸ���������ġ�
	// ʣ�¾���������ĸ����϶�����
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

	// LeetCode����Ĵ𰸣����Ÿ����һ�㣿
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
