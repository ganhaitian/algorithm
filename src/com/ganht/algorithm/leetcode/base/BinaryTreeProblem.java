package com.ganht.algorithm.leetcode.base;

/**
 * 二叉树类别的问题
 */
public abstract class BinaryTreeProblem {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 从数组中构建一颗二叉树
     * @param input
     * @return
     */
    protected static TreeNode buildTreeFromArray(Integer[] input){
        TreeNode[] nodes = new TreeNode[input.length];
        TreeNode root = null;
        for (int i = 0; i < input.length; i++) {
            Integer val = input[i];
            if (val == null) {
                continue;
            }

            TreeNode node = nodes[i] == null ? new TreeNode(val) : nodes[i];
            if(i == 0){
                root = node;
            }

            int leftIndex = (i * 2) + 1;
            if(leftIndex < input.length){
                Integer leftVal = input[leftIndex];
                if (leftVal != null) {
                    nodes[leftIndex] = new TreeNode(leftVal);
                    node.left = nodes[leftIndex];
                }
            }

            int rightIndex = (i * 2) + 2;
            if(rightIndex < input.length){
                Integer rightVal = input[rightIndex];
                if (rightVal != null) {
                    nodes[rightIndex] = new TreeNode(rightVal);
                    node.right = nodes[rightIndex];
                }
            }
        }

        return root;
    }

}
