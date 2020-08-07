package com.ganht.algorithm.leetcode;

/**
 * For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
 *
 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
 *
 * Write a function that determines whether two binary trees are flip equivalent.  The trees are given by root nodes root1 and root2.
 *
 *
 *
 * Example 1:
 *
 * Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * Output: true
 * Explanation: We flipped at nodes with values 1, 3, and 5.
 * Flipped Trees Diagram
 *
 *
 * Note:
 *
 * Each tree will have at most 100 nodes.
 * Each value in each tree will be a unique integer in the range [0, 99].
 *
 * @author haitian.gan
 */
public class FlipEquivalentBinaryTrees {

    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public String toString(){
            return String.valueOf(val);
        }
    }

    /**
     * 应该是用递归就能搞定
     * @param root1
     * @param root2
     * @return
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 != null && root2 != null && root1.val == root2.val) {
            // 有正常和翻转两种情况，任何一种情况满足条件都是true
            boolean flip = flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
            if (flip) {
                return true;
            }

            boolean normal = flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root1.right);
            if (normal) {
                return true;
            }

            return false;
        } else {
            return false;
        }
    }

    private static TreeNode buildTreeNode(Integer[] vals) {
        TreeNode[] nodeArrays = new TreeNode[vals.length];
        for (int i = 0; i < vals.length; i++) {
            if (i == 0) {
                nodeArrays[0] = new TreeNode(vals[0]);
            } else if(vals[i] != null){
                TreeNode newNode = new TreeNode(vals[i]);
                if (i % 2 == 1) {
                    nodeArrays[i / 2].left = newNode;
                } else {
                    nodeArrays[(i-1) / 2].right = newNode;
                }

                nodeArrays[i] = newNode;
            }
        }

        return nodeArrays[0];
    }

    public static void main(String[] args){
        TreeNode node1 = buildTreeNode(new Integer[]{1,2,3,4,5,6,null,null,null,7,8});
        TreeNode node2 = buildTreeNode(new Integer[]{1,3,2,null,6,4,5,null,null,null,null,null,null,8,7});
        System.out.println(new FlipEquivalentBinaryTrees().flipEquiv(node1,node2));
    }

}
