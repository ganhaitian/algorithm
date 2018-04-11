package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 *
 *  Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element
 *  in the matrix.

    Note that it is the kth smallest element in the sorted order, not the kth distinct element.

    Example:

     matrix = [
     [ 1,  5,  9],
     [10, 11, 13],
     [12, 13, 15]
     ],
     k = 8,

     return 13.
     Note:
     You may assume k is always valid, 1 ≤ k ≤ n2.
 *
 */
public class KthSmallestInASortedMatrix {

    /**
     * 这道题和有序数组取交集有些相似，区别就是从两个数组变成了N维数组。
     * 思路就是比较每一维数组的头部，取最小的那一行，向后遍历。
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        // 记录了每个数字所在的行
        Map<Integer,Deque<Integer>> posMap = new HashMap<>();
        TreeSet<Integer> headSet = new TreeSet<>();
        int n = matrix.length;
        // 记录了每行探到了哪个位置
        int[] deep = new int[n];

        int head;
        for(int i = 0;i < matrix.length;i ++){
            head = matrix[i][0];

            headSet.add(head);

            Deque<Integer> rows = posMap.getOrDefault(head, new LinkedList<>());
            rows.add(i);
            posMap.put(head,rows);
        }

        int count = 0;
        int result = 0;
        while(true){
            Integer min = headSet.pollFirst();
            if(min == null)
                break;

            count ++;
            if(count == k){
                result = min;
                break;
            }

            Deque<Integer> rows = posMap.get(min);
            int currRow = rows.poll();
            // 可能是并列大小的情况，弹出来一个后，要放回去
            if(rows.size() > 0){
                headSet.add(min);
            }

            int currCol = ++ deep[currRow];
            // 如果这一行已经全部看过了，则跳过
            if(currCol >= n)
                continue;

            int next = matrix[currRow][currCol];
            headSet.add(next);

            rows = posMap.getOrDefault(next, new LinkedList<>());
            rows.add(currRow);
            posMap.put(next, rows);
        }

        return result;
    }

    public static void main(String[] args){
        int[][] input = {{1,2}, {1,3}};
        System.out.println(new KthSmallestInASortedMatrix().kthSmallest(input,2));
    }

}
