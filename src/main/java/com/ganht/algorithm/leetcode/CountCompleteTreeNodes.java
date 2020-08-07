package com.ganht.algorithm.leetcode;

/**
 * Given a complete binary tree, count the number of nodes.
 *
 * Note:
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level
 * are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Example:
 *
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * Output: 6
 * @author haitian.gan
 */
public class CountCompleteTreeNodes {

    public class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 这种算暴力式破解，太慢了，其实用二分要快很多
    @Deprecated
    public int countNodes(TreeNode root) {
        TreeNode left       = root;
        int      leftHeight = 0;
        while (left.left != null) {
            leftHeight++;
        }

        TreeNode right       = root;
        int      rightHeight = 0;
        while (right.right != null) {
            right = right.right;
            rightHeight++;
        }

        if (leftHeight == rightHeight) {
            return (int) Math.pow(2, leftHeight) - 1;
        }

        int count = 0;
        while (true) {
            if (right.right != null) {
                break;
            } else {
                count++;
                if (right.left != null) {
                    break;
                } else {
                    count++;
                    right = findLeft(right);
                }
            }

            right = findLeft(right);
        }

        return (int) Math.pow(2, leftHeight) - 1 - count;
    }

    public void counterNodes1(){
        
    }

    public TreeNode findLeft(TreeNode left){
        return null;
    }

}
