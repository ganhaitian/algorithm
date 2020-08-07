package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.
 *
 * For each integer in this list:
 *
 * The hundreds digit represents the depth D of this node, 1 <= D <= 4.
 * The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the
 * same as that in a full binary tree.
 * The units digit represents the value V of this node, 0 <= V <= 9.
 *
 *
 * Given a list of ascending three-digits integers representing a binary tree with the depth smaller than 5, you need
 * to return the sum of all paths from the root towards the leaves.
 *
 * Example 1:
 *
 * Input: [113, 215, 221]
 * Output: 12
 * Explanation:
 * The tree that the list represents is:
 *     3
 *    / \
 *   5   1
 *
 * The path sum is (3 + 5) + (3 + 1) = 12.
 *
 * Example 2:
 *
 * Input: [113, 221]
 * Output: 4
 * Explanation:
 * The tree that the list represents is:
 *     3
 *      \
 *       1
 *
 * The path sum is (3 + 1) = 4.
 * @author haitian.gan
 */
public class PathSumIV {

    private Map<Integer, Integer> indexMap;
    private int                   sum;

    public int pathSum(int[] nums) {
        indexMap = new HashMap<>();
        for (int num : nums) {
            indexMap.put(num / 10, num % 10);
        }

        calLeaveNum(11);
        return sum;
    }

    /**
     * 计算叶子结点的数量
     * @param index
     * @return
     */
    public int calLeaveNum(int index) {
        Integer num = indexMap.get(index);
        if (num == null) {
            return 0;
        }

        // 获得左和右
        int depth    = index / 10;
        int position = index % 10;
        int right    = (depth + 1) * 10 + position * 2;
        int left     = right - 1;

        int rightNum = calLeaveNum(right);
        int leftNum  = calLeaveNum(left);

        if (rightNum == 0 && leftNum == 0) {
            sum += num;
            return 1;
        } else {
            int leaveNum = rightNum + leftNum;
            sum += leaveNum * num;
            return leaveNum;
        }
    }

    public static void main(String[] args) {
        int[] nums = {113, 215, 221, 336, 347};
        new PathSumIV().pathSum(nums);
    }

}
