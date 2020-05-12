package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.leetcode.base.BinaryTreeProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a binary tree, determine if it is a complete binary tree.
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level
 * are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the
 * last level ({4, 5, 6}) are as far left as possible.
 * Example 2:
 *
 *
 *
 * Input: [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 *
 * Note:
 *
 * The tree will have between 1 and 100 nodes.
 * @author haitian.gan
 */
public class CheckCompletenessOfABinaryTree extends BinaryTreeProblem {

    private int maxLvl;
    //private int                   onlyLeftNum;
    private Map<Integer, Integer> levelMap = new HashMap<>();

    public boolean isCompleteTree(TreeNode root) {
        TreeNode leftNode = root;
        while(leftNode != null){
            leftNode = leftNode.left;
            maxLvl = maxLvl + 1;
        }

        return traverse(root, 1);
    }

    private boolean traverse(TreeNode node, int level) {
        if (node == null) {
            return true;
        }

        if (level > maxLvl) {
            return false;
        }

        levelMap.put(level, 1);

        TreeNode left  = node.left;
        TreeNode right = node.right;
        if (left != null && right == null) {
            if (levelMap.containsKey(level)) {
                return false;
            }
        }else if(left == null && right != null){
            return false;
        }

        return traverse(right, level + 1) && traverse(left, level + 1);
    }

    // 用到了完全二叉树结点数量的一个小公式
    public boolean isCompleteTree1(TreeNode root) {
        List<ANode> nodes = new ArrayList();
        nodes.add(new ANode(root, 1));
        int i = 0;
        while (i < nodes.size()) {
            ANode anode = nodes.get(i++);
            if (anode.node != null) {
                nodes.add(new ANode(anode.node.left, anode.code * 2));
                nodes.add(new ANode(anode.node.right, anode.code * 2 + 1));
            }
        }

        return nodes.get(i - 1).code == nodes.size();
    }

    class ANode {  // Annotated Node
        TreeNode node;
        int      code;

        ANode(TreeNode node, int code) {
            this.node = node;
            this.code = code;
        }

    }

    /**
     * 这个好，用的广度优先搜索，就是像个波一样，一层一层的搜索整棵数，
     * 1. bfs and the queue finally should have all the leaves including null
     * 2. for a complete binary tree, there should not be any node after we met a null node in the final level
     * class Solution(object):
     *     def isCompleteTree(self, root):
     *         """
     *         :type root: TreeNode
     *         :rtype: bool
     *         """
     *         q = [root]
     *         while q[0] != None:
     *             node = q.pop(0)
     *             q.append(node.left)
     *             q.append(node.right)
     *         while len(q) > 0 and q[0] == None:
     *             q.pop(0)
     *         return len(q) == 0
     * @param args
     */

    public static void main(String[] args){
        Integer[] nums = {1,2,3,5,null,7,8};
        System.out.println(new CheckCompletenessOfABinaryTree().isCompleteTree(buildTreeFromArray(nums)));
    }

}
