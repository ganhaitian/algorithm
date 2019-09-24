package com.ganht.algorithm.leetcode;

import java.util.ListResourceBundle;

/**
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a
 * subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could
 * also be considered as a subtree of itself.
 *
 * Example 1:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 * Example 2:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return false.
 * @author haitian.gan
 */
public class SubtreeOfAnotherTree {

    public class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return false;
        }

        boolean result;
        if (s.val == t.val) {
            result = isTheSame(s, t);
            if (result) {
                return true;
            }
        }

        result = isSubtree(s.left, t);
        if (result) {
            return true;
        }

        result = isSubtree(s.right, t);
        if (result) {
            return true;
        }

        return false;
    }

    private boolean isTheSame(TreeNode a, TreeNode b){
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        if(a.val == b.val){
            boolean r1 = isTheSame(a.left,b.left);
            boolean r2 = isTheSame(a.right,b.right);
            return r1 && r2;
        }

        return false;
    }

}
