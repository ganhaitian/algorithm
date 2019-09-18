package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes
 * left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)
 *
 * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to
 * the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists.
 * If not, travel to the right subtree. Repeat until you reach a leaf node.
 *
 * The right-most node is also defined by the same way with left and right exchanged.
 *
 * Example 1
 *
 * Input:
 *   1
 *    \
 *     2
 *    / \
 *   3   4
 *
 * Ouput:
 * [1, 3, 4, 2]
 *
 * Explanation:
 * The root doesn't have left subtree, so the root itself is left boundary.
 * The leaves are node 3 and 4.
 * The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 * So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 *
 *
 * Example 2
 *
 * Input:
 *     ____1_____
 *    /          \
 *   2            3
 *  / \          /
 * 4   5        6
 *    / \      / \
 *   7   8    9  10
 *
 * Ouput:
 * [1,2,4,7,8,9,10,6,3]
 *
 * Explanation:
 * The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 * The leaves are node 4,7,8,9,10.
 * The right boundary are node 1,3,6,10. (10 is the right-most node).
 * So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 * @author haitian.gan
 */
public class BoundaryOfBinaryTree {

    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /** 结果 **/
    private List<Integer> result = new ArrayList<>();

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if(root == null){
            return Collections.emptyList();
        }

        result.add(root.val);

        traversal1(root.left, 1);
        traversal2(root.right, 1);
        return result;
    }

    // 中、左、右，这么个顺序
    private void traversal1(TreeNode node, int parentFlag) {
        if (node == null) {
            return;
        }

        if (parentFlag == 1) {
            result.add(node.val);
        }else if(node.left == null && node.right == null){
            result.add(node.val);
        }

        int flag = parentFlag;
        traversal1(node.left, flag);

        if(node.left != null && parentFlag == 1){
            flag = 0;
        }
        traversal1(node.right, flag);
    }

    // 左、右、中，这么个顺序
    private void traversal2(TreeNode node, int parentFlag) {
        if (node == null) {
            return;
        }

        int flag = parentFlag;
        if(node.right != null && parentFlag == 1){
            flag = 0;
        }
        traversal2(node.left, flag);

        flag = parentFlag;
        traversal2(node.right, flag);

        if (parentFlag == 1) {
            result.add(node.val);
        }else if(node.left == null && node.right == null){
            result.add(node.val);
        }
    }

    public static void main(String[] args) {
        TreeNode _1node  = new TreeNode(1);
        TreeNode _2node  = new TreeNode(2);
        TreeNode _3node  = new TreeNode(3);
        TreeNode _4node  = new TreeNode(4);
        TreeNode _5node  = new TreeNode(5);
        TreeNode _6node  = new TreeNode(6);
        TreeNode _7node  = new TreeNode(7);
        TreeNode _8node  = new TreeNode(8);
        TreeNode _9node  = new TreeNode(9);
        TreeNode _10node = new TreeNode(10);

        _1node.left = _2node;
        _1node.right = _3node;
        _2node.left = _4node;
        _2node.right = _5node;
        _5node.left = _7node;
        _5node.right = _8node;
        _3node.left = _6node;
        _6node.left = _9node;
        _6node.right = _10node;

        List<Integer> result = new BoundaryOfBinaryTree().boundaryOfBinaryTree(_1node);
        System.out.println(result);
    }

}
