package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haitian.gan
 */
public class SearchA2DMatrixII {

    private Map<Integer, Boolean> rowCache = new HashMap<>();
    private Map<Integer, Boolean> colCache = new HashMap<>();

    private class Node{
        private int x;
        private int y;
        private boolean clear;

        private Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 这一题的关键是起点的问题，如何选取起点可以让移动变成唯一的选项
     */
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            // start our "pointer" in the bottom-left
            int row = matrix.length-1;
            int col = 0;

            while (row >= 0 && col < matrix[0].length) {
                if (matrix[row][col] > target) {
                    row--;
                } else if (matrix[row][col] < target) {
                    col++;
                } else { // found it
                    return true;
                }
            }

            return false;
        }
    }

/*    public boolean searchMatrix(int[][] matrix, int target) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;

        int start = matrix[0][0];
        LinkedList<Node> accessList = new LinkedList<>();
        accessList.push(new Node(0,0));

        while(accessList.size() > 0){
            Node tmp = accessList.poll();
            if(tmp.clear){
                accessList.pop();
            }

            for (int col = tmp.y; col < colNum && rowCache.get(tmp.x) != null; col++) {
                if(matrix[tmp.x][col] == target){
                    return true;
                }else if(matrix[tmp.x][col] > target){
                    tmp.clear = true;
                    rowCache.put(tmp.x, true);
                    accessList.pop();
                }
            }

            for (int row = tmp.x; row < rowNum && colCache.get(tmp.y) != null; row++) {
                if(matrix[row][tmp.y] == target ){
                    return true;
                }else if(matrix[row][tmp.y] > target){
                    tmp.clear = true;
                    colCache.put(tmp.y, true);
                    accessList.pop();
                }
            }
        }

        return false;
    }*/

}
