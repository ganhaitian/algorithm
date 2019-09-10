package com.ganht.algorithm.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a binary tree, return the vertical order traversal of its nodes values.
 *
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 *
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report
 * the values of the nodes in order from top to bottom (decreasing Y coordinates).
 *
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 *
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Without loss of generality, we can assume the root node is at position (0, 0):
 * Then, the node with value 9 occurs at position (-1, -1);
 * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 * The node with value 20 occurs at position (1, -1);
 * The node with value 7 occurs at position (2, -2).
 * Example 2:
 *
 *
 *
 * Input: [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * The node with value 5 and the node with value 6 have the same position according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 *
 *
 * Note:
 *
 * The tree will have between 1 and 1000 nodes.
 * Each node's value will be between 0 and 1000.
 * @author haitian.gan
 */
public class VerticalOrderdTraversalOfABinaryTree {

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
    }

    private Map<Integer, Vertical> map = new TreeMap<>();

    private static class Vertical implements Comparable<Vertical>{
        private Set<NodeWrapper> nodeValueList = new TreeSet<>();
        private int x;

        @Override
        public int compareTo(Vertical o) {
            return Integer.compare(x, o.x);
        }
    }

    private static class NodeWrapper implements Comparable<NodeWrapper>{
        private TreeNode node;
        private int y;

        NodeWrapper(TreeNode node,int y){
            this.node = node;
            this.y = y;
        }

        @Override
        public int compareTo(NodeWrapper o) {
            int firstCompare = Integer.compare(o.y, y);
            if(firstCompare != 0){
                return firstCompare;
            }

            return Integer.compare(node.val, o.node.val);
        }
    }

    // 感觉用个中序遍历，再辅助两个结构就行
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        traversal(root, 0, 0);
        return map.values()
                .stream()
                .map(v -> v.nodeValueList.stream().map(n -> n.node.val).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private void traversal(TreeNode node, int x, int y) {
        if (node == null) {
            return;
        }

        map.compute(x, (k, v) -> {
            if (v == null) {
                v = new Vertical();
            }

            v.nodeValueList.add(new NodeWrapper(node, y));
            return v;
        });

        traversal(node.left, x - 1, y - 1);
        traversal(node.right, x + 1, y - 1);
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
        TreeNode root = buildTreeNode(new Integer[]{1,2,3,4,5,6,7});
        new VerticalOrderdTraversalOfABinaryTree().verticalTraversal(root);
    }

}
