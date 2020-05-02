package com.ganht.algorithm.leetcode;


import com.ganht.algorithm.leetcode.base.BinaryTreeProblem;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 * Follow up: Solve it both recursively and iteratively.
 */
public class SymmetricTree extends BinaryTreeProblem {

    // 先翻转，再比较
    public boolean isSymmetric(TreeNode root) {
        TreeNode reverseNode = reverse(root);
        return isEqual(reverseNode, root);
    }

    private TreeNode reverse(TreeNode node){
        if( node == null){
            return null;
        }

        TreeNode newNode = new TreeNode(node.val);
        newNode.left = reverse(node.right);
        newNode.right = reverse(node.left);
        return newNode;
    }

    private boolean isEqual(TreeNode a, TreeNode b){
        if(a == null && b == null){
            return true;
        }

        if(a == null || b == null){
            return false;
        }

        if(a.val != b.val){
            return false;
        }

        return isEqual(a.left, b.left) && isEqual(a.right, b.right);
    }

    public static void main(String[] args){
        Integer[] input = new Integer[]{1,2,3,3,4,4,3};
        TreeNode root = BinaryTreeProblem.buildTreeFromArray(input);
        System.out.println(new SymmetricTree().isSymmetric(root));
    }

}
