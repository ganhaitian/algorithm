package com.ganht.algorithm.base;

import java.util.function.Function;

/**
 * 二叉树类型的问题
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
        if(input == null){
            return null;
        }

        TreeNode[] nodes = new TreeNode[input.length];
        Function<Integer, TreeNode> initTreeNodeFunc = index -> {
            if (index < input.length) {
                Integer val = input[index];
                if (val != null) {
                    nodes[index] = new TreeNode(val);
                    return nodes[index];
                }
            }

            return null;
        };

        TreeNode root = null;
        int leftIndex,rightIndex;
        for (int i = 0; i < input.length; i++) {
            Integer val = input[i];
            if (val == null) {
                continue;
            }

            TreeNode node = nodes[i] == null ? new TreeNode(val) : nodes[i];
            if(i == 0){
                root = node;
            }

            leftIndex = (i * 2) + 1;
            node.left = initTreeNodeFunc.apply(leftIndex);

            rightIndex = leftIndex + 1;
            node.right = initTreeNodeFunc.apply(rightIndex);
        }

        return root;
    }

}
