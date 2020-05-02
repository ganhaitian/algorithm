package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.leetcode.base.BinaryTreeProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal extends BinaryTreeProblem {

    private Map<Integer, List<Integer>> levelMap = new HashMap<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }

        traverse(root, 1);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 1;;i = i + 1){
            List<Integer> nodeList = levelMap.get(i);
            if(nodeList == null || nodeList.size() == 0){
                break;
            }

            result.add(nodeList);
        }

        return result;
    }

    private void traverse(TreeNode node, int level){
        levelMap.computeIfAbsent(level, k -> new ArrayList<>()).add(node.val);

        int nextLevel = level + 1;
        List<Integer> nodeList = levelMap.computeIfAbsent(nextLevel, k -> new ArrayList<>());

        TreeNode left = node.left;
        if(left != null){
            //nodeList.add(left.val);
            traverse(left, nextLevel);
        }

        TreeNode right = node.right;
        if(right != null){
            //nodeList.add(right.val);
            traverse(right, nextLevel);
        }
    }

    public static void main(String[] args) {
        Integer[] input = new Integer[]{3, 9, 20, null, null, 15, 7};
        TreeNode root = BinaryTreeProblem.buildTreeFromArray(input);

        new BinaryTreeLevelOrderTraversal().levelOrder(root);
    }

}
