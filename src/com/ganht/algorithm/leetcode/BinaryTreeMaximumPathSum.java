package com.ganht.algorithm.leetcode;

import java.util.Arrays;

/**
 *   Binary Tree Maximum Path Sum
     DescriptionHintsSubmissionsDiscussSolution
     Pick One
     Given a non-empty binary tree, find the maximum path sum.

     For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the
     parent-child connections. The path must contain at least one node and does not need to go through the root.

     Example 1:

     Input: [1,2,3]

     1
     / \
     2   3

     Output: 6
     Example 2:

     Input: [-10,9,20,null,null,15,7]

     -10
     / \
     9  20
     /  \
     15   7

     Output: 42
 */
public class BinaryTreeMaximumPathSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxPathSum(TreeNode root) {
        int[] res = {Integer.MIN_VALUE};
        max(root,res);
        return res[0];
    }

    private int max(TreeNode node,int[] res){
        if(node == null)
            return 0;

        int left = Math.max(max(node.left,res),0);
        int right = Math.max(max(node.right,res),0);
        res[0] = Math.max(left + right + node.val,res[0]);

        return Math.max(left,right)+ node.val;
    }

}
