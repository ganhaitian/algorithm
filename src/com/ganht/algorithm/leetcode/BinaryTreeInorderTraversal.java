package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.leetcode.base.BinaryTreeProblem;

import java.util.*;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal extends BinaryTreeProblem {

    private List<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        traversal(root);
        return result;
    }

    private void traversal(TreeNode root){
        if(root == null){
            return;
        }

        traversal(root.left);
        result.add(root.val);
        traversal(root.right);
    }

    // 不用递归试试
    public List<Integer> inorderTraversal1(TreeNode root) {
        HashSet<TreeNode> existSet = new HashSet<>();
        Deque<TreeNode> tmp = new LinkedList<>();
        tmp.push(root);

        List<Integer> result = new ArrayList<>();
        while(!tmp.isEmpty()){
            TreeNode node = tmp.pop();
            if(existSet.contains(node)){
               continue;
            }

            if(node.left == null || tmp.contains(node.left)){
                result.add(node.val);
                existSet.add(node);
                continue;
            }

            if(node.right != null){
                tmp.push(node.right);
            }

            tmp.push(node);

            if(node.left != null){
                tmp.push(node.left);
            }
        }

        return result;
    }

    public static void main(String[] args){

    }

}
