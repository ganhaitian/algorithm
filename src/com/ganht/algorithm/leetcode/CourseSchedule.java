package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed
 * as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph
 * is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * @author haitian.gan
 */
public class CourseSchedule {

    private Map<Integer, List<Integer>> preMap     = new HashMap<>();
    private Set<Integer>                learnedSet = new HashSet<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int[] prePair : prerequisites) {
            preMap.computeIfAbsent(prePair[0], k -> new ArrayList<>()).add(prePair[1]);
        }

        for (int i = 0; i < numCourses; i++) {
            Set<Integer> path = new HashSet<>();
            path.add(i);
            if (!learn(i, path)) {
                return false;
            }
        }

        return true;
    }

    private boolean learn(int courseId, Set<Integer> path) {
        if (learnedSet.contains(courseId)) {
            return true;
        }

        if (!preMap.containsKey(courseId)) {
            learnedSet.add(courseId);
            return true;
        }

        // 获得所有前置的结点
        List<Integer> preList = preMap.get(courseId);
        for (Integer preId : preList) {
            if (path.contains(preId)) {
                return false;
            }

            if (!learnedSet.contains(preId)) {
                Set<Integer> newPath = new HashSet<>(path);
                newPath.add(preId);

                if (!learn(preId, newPath)) {
                    return false;
                }

                learnedSet.add(preId);
            }
        }

        return true;
    }

    public static void main(String[] args){
        boolean result = new CourseSchedule().canFinish(2,new int[][]{{1,0},{0,1}});
        System.out.println(result);
    }

}
