package com.ganht.algorithm.leetcode;

/**
 * Invert Binary Tree
 * Created by haitian.gan on 2017/7/3.
 */
public class InvertBinaryTree {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;

        TreeNode leftNode = root.left;
        if(leftNode != null)
            invertTree(leftNode);

        TreeNode rightNode = root.right;
        if(rightNode != null)
            invertTree(rightNode);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

}
