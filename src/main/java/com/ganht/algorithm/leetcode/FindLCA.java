package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.base.BinaryTreeProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
   According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
   two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

              _______3______
            /              \
         ___5__          ___1__
         /      \       /      \
         6      _2     0       8
       /  \
      7   4

    For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5,
 since a node can be a descendant of itself according to the LCA definition.


 从这里开始是新的题目描述
 Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
 the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]




 Example 1:

 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 Output: 3
 Explanation: The LCA of nodes 5 and 1 is 3.
 Example 2:

 Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 Output: 5
 Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


 Note:

 All of the nodes' values will be unique.
 p and q are different and both values will exist in the binary tree.


    Subscribe to see which companies asked this question
 *  Created by 17ZY-HPYKFD2 on 2016/10/20.
 */
public class FindLCA extends BinaryTreeProblem {

    private List<TreeNode> pLink;
    private List<TreeNode> qLink;

    // 这个思路超级清晰，但可惜没有去做验证
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        visitNode(root, p, q, new ArrayList<>());
        TreeNode prev = null;
        for (int i = 0; i < Math.min(pLink.size(), qLink.size()); i++) {
            if (pLink.get(i) != qLink.get(i)) {
                return prev;
            } else {
                prev = pLink.get(i);
            }
        }

        return prev;
    }

    public void visitNode(TreeNode a, TreeNode p, TreeNode q, List<TreeNode> history) {
        if (a == null) {
            return;
        }

        // 如果p和q都已经找到了，就不需要再遍历
        if(pLink != null && qLink != null){
            return;
        }

        if (a == p) {
            pLink = history;
        }

        if (a == q) {
            qLink = history;
        }

        var left       = a.left;
        var newHistory = new ArrayList<>(history);
        newHistory.add(left);
        visitNode(left, p, q, newHistory);

        var right = a.right;
        newHistory = new ArrayList<>(history);
        newHistory.add(right);
        visitNode(right, p, q, newHistory);
    }

    public static void main(String[] args) {
        TreeNode root = deserialize("3,5,1,6,2,0,8,null,null,7,4");
        new FindLCA().lowestCommonAncestor(root, root.left.left, root.left.right.right);
    }

    /**
     * 这个挺巧妙的
     * class Solution {
     *
     *     private TreeNode ans;
     *
     *     public Solution() {
     *         // Variable to store LCA node.
     *         this.ans = null;
     *     }
     *
     *     private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
     *
     *         // If reached the end of a branch, return false.
     *         if (currentNode == null) {
     *             return false;
     *         }
     *
     *         // Left Recursion. If left recursion returns true, set left = 1 else 0
     *         int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;
     *
     *         // Right Recursion
     *         int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;
     *
     *         // If the current node is one of p or q
     *         int mid = (currentNode == p || currentNode == q) ? 1 : 0;
     *
     *
     *         // If any two of the flags left, right or mid become True
     *         if (mid + left + right >= 2) {
     *             this.ans = currentNode;
     *         }
     *
     *         // Return true if any one of the three bool values is True.
     *         return (mid + left + right > 0);
     *     }
     *
     *     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
     *         // Traverse the tree
     *         this.recurseTree(root, p, q);
     *         return this.ans;
     *     }
     */
}
