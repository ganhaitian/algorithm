package com.ganht.algorithm.leetcode;

/**
 * haitian.gan on 2017/6/15.
 */
public class LongtestConsecutiveSequenceInBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int longestConsecutive(TreeNode root) {
        return preTravel(null,root,1);
    }

    public int preTravel(TreeNode parent,TreeNode node,int length){
        if(node == null)
            return 0;

        if(parent != null && node.val == parent.val + 1)
            length ++;
        else
            length = 1;

        int leftLength = 0;
        if(node.left != null)
            leftLength = preTravel(node,node.left,length);
        else
            leftLength = length;

        int rightLength = 0;
        if(node.right != null)
            rightLength = preTravel(node,node.right,length);
        else
            rightLength = length;

        return Math.max(leftLength,rightLength);
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);

        TreeNode _2node = new TreeNode(2);
        root.left = _2node;

        TreeNode _3node = new TreeNode(3);
        root.right = _3node;

        TreeNode _4node = new TreeNode(4);
        _3node.left = _4node;

        TreeNode _5node = new TreeNode(5);
        _3node.right = _5node;

        System.out.println(new LongtestConsecutiveSequenceInBinaryTree().longestConsecutive(root));
    }

    /**
     * 这是leetcode上面的答案
     * private int max = 0;
     public int longestConsecutive(TreeNode root) {
     if(root == null) return 0;
     helper(root, 0, root.val);
     return max;
     }

     public void helper(TreeNode root, int cur, int target){
     if(root == null) return;
     if(root.val == target) cur++;
     else cur = 1;
     max = Math.max(cur, max);
     helper(root.left, cur, root.val + 1);
     helper(root.right, cur, root.val + 1);
     }
     *
     */

}
