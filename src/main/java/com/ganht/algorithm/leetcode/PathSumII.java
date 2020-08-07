package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

    For example:
    Given the below binary tree and sum = 22,
     5
     / \
     4   8
     /   / \
     11  13  4
     /  \    / \
     7    2  5   1

     return
     [
     [5,4,11,2],
     [5,8,4,5]
     ]
 * Created by ganhaitian on 2017/1/27.
 */
public class PathSumII {


      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
     }


    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null)
            return Collections.emptyList();

        if(root.val == sum && isLeafNode(root))
            return Collections.singletonList(Collections.singletonList(root.val));

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();
        trace.add(root.val);

        sumNode(root,root.val,sum, trace, result);
        return result;
    }

    private void sumNode(TreeNode node, int accuSum, int sum, List<Integer> trace, List<List<Integer>> result){

        int leftSum = 0;
        if(node.left != null){
            leftSum += accuSum + node.left.val;
            List<Integer> newTraceCopy = new ArrayList<>();
            newTraceCopy.addAll(trace);
            if(!isLeafNode(node.left)) {

                newTraceCopy.add(node.left.val);
                sumNode(node.left, leftSum, sum, newTraceCopy, result);
            }
            else if(leftSum == sum){
                newTraceCopy.add(node.left.val);
                result.add(newTraceCopy);
            }
        }

        int rightSum = 0;
        if(node.right != null){
            rightSum += accuSum + node.right.val;
            List<Integer> newTraceCopy = new ArrayList<>();
            newTraceCopy.addAll(trace);
            if(!isLeafNode(node.right)) {
                newTraceCopy.add(node.right.val);
                sumNode(node.right, rightSum, sum, newTraceCopy, result);
            }
            else if(rightSum == sum){
                newTraceCopy.add(node.right.val);
                result.add(newTraceCopy);
            }

        }

    }

    private boolean isLeafNode(TreeNode node){
        return node != null && node.left == null && node.right == null;
    }

    public static void main(String[] args){
        /*TreeNode root = new TreeNode(5);

        TreeNode _1node = new TreeNode(4);
        TreeNode _2node = new TreeNode(8);
        root.left = _1node;
        root.right = _2node;

        TreeNode _3node = new TreeNode(11);
        _1node.left = _3node;

        TreeNode _4node = new TreeNode(13);
        TreeNode _5node = new TreeNode(4);
        _2node.left = _4node;
        _2node.right = _5node;

        TreeNode _6node = new TreeNode(7);
        TreeNode _7node = new TreeNode(2);
        _3node.left = _6node;
        _3node.right = _7node;

        TreeNode _8node = new TreeNode(5);
        TreeNode _9node = new TreeNode(1);
        _5node.left = _8node;
        _5node.right = _9node;*/

       /* TreeNode root = new TreeNode(0);
        TreeNode _1node = new TreeNode(1);
        TreeNode _2node = new TreeNode(1);
        root.left = _1node;
        root.right = _2node;*/

       Integer[] array = new Integer[]{1,-2,-3,1,3,-2,null,-1};

        new PathSumII().pathSum(buildTreeFromArray(array),-1);
    }

    private static TreeNode buildTreeFromArray(Integer[] input){
        TreeNode root = null;
        TreeNode[] nodes = new TreeNode[input.length];
        for(int i =0;i < input.length;i ++){
            if(input[i] == null)
                continue;

            TreeNode tmp = nodes[i];
            if(tmp == null) {
                tmp = new TreeNode(input[i]);
                nodes[i] = tmp;
            }

            if(i == 0)
                root = tmp;

            int leftIndex = i * 2 + 1;
            int rightIndex = (i + 1) * 2;
            if(leftIndex <= input.length - 1 && input[leftIndex] != null){
                TreeNode left = new TreeNode(input[leftIndex]);
                tmp.left = left;
                nodes[leftIndex] = left;
            }

            if(rightIndex <= input.length - 1 && input[rightIndex] != null){
                TreeNode right = new TreeNode(input[rightIndex]);
                tmp.right = right;
                nodes[rightIndex] = right;
            }
        }

        return root;
    }

}
