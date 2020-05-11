package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.leetcode.base.BinaryTreeProblem;

import java.util.*;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see
 * ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * @author haitian.gan
 */
public class BinaryTreeRightSideView extends BinaryTreeProblem {

    private static class Entry{
        private final TreeNode node;
        private final int      order;
        Entry(TreeNode node, int order){
            this.node = node;
            this.order = order;
        }
    }

    private Map<Integer, Entry> maxMap = new TreeMap<>();

    public List<Integer> rightSideView(TreeNode root) {
        traverse(root, 1, 1);
        List<Integer> result = new ArrayList<>();
        for(Map.Entry<Integer, Entry> e : maxMap.entrySet()){
            result.add(e.getValue().node.val);
        }

        return result;
    }

    private void traverse(TreeNode node,int level, int order){
        if(node == null){
            return;
        }

        maxMap.compute(level, (k,oldV) -> {
            Entry e = new Entry(node, order);
            if(oldV == null){
                return e;
            }else{
                return oldV.order < order ? e : oldV;
            }
        });

        int newLevel = level + 1;
        int leftOrder = order * 2 - 1;
        traverse(node.left, newLevel, leftOrder);
        traverse(node.right, newLevel, leftOrder + 1);
    }

    // DFS的方案
    public List<Integer> rightSideView1(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;

        /* These two stacks are always synchronized, providing an implicit
         * association values with the same offset on each stack. */
        Stack<TreeNode> nodeStack  = new Stack<TreeNode>();
        Stack<Integer>  depthStack = new Stack<Integer>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if (node != null) {
                max_depth = Math.max(max_depth, depth);

                /* The first node that we encounter at a particular depth contains
                 * the correct value. */
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }

                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth+1);
                depthStack.push(depth+1);
            }
        }

        /* Construct the solution based on the values that we end up with at the
         * end. */
        List<Integer> rightView = new ArrayList<Integer>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }

    public static void main(String[] args) {
        Integer[] input = {1, 2, 3, null, 5, null, 4};
        new BinaryTreeRightSideView().rightSideView(buildTreeFromArray(input));
    }

}
