package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.base.BinaryTreeProblem;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Example 1:
 *
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * Example 2:
 *
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * Follow up:
 *
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 * @author haitian.gan
 */
public class RecoverBinarySearchTree extends BinaryTreeProblem {

    public void recoverTree(TreeNode root) {
        traverse(root);
    }

    // 思路，不断遍历，找到每个结点左子数的最大值和右子树的最小值和当前值进行比较，如果有异常，就进行交换
    // 返回值的第一个元素是最小结点，第二个元素是最大结点
    // 唉，傻逼了，记住，要充分审题，利用bst的性质，不就是有序数组吗
    private TreeNode[] traverse(TreeNode node) {
        if (node == null) {
            return null;
        }

        var left  = node.left;
        var right = node.right;

        var leftResult  = traverse(left);
        var rightResult = traverse(right);

        var min = leftResult == null ? node : leftResult[0];
        var max = rightResult == null ? node : rightResult[1];

        TreeNode swap1       = null, swap2 = null;
        boolean  leftProblem = false;
        // 这个是有问题的
        if (leftResult != null && node.val < leftResult[1].val) {
            leftProblem = true;
            swap1       = node;
            swap2       = leftResult[1];
        }

        boolean rightProblem = false;
        if (rightResult != null && node.val > rightResult[0].val) {
            rightProblem = true;
            swap1        = node;
            swap2        = rightResult[0];
        }

        if (rightProblem && leftProblem) {
            swap1 = leftResult[1];
            swap2 = rightResult[0];
        }

        if (leftProblem || rightProblem) {
            var tmp = swap1.val;
            swap1.val = swap2.val;
            swap2.val = tmp;
        }

        return new TreeNode[]{min, max};
    }

    public static void main(String[] args) {
        Integer[] input = {3, 1, 4, null, null, 2};
        var       root  = buildTreeFromArray(input);
        new RecoverBinarySearchTree().recoverTree(root);
    }

    /**
     * class Solution {
     *   public void inorder(TreeNode root, List<Integer> nums) {
     *     if (root == null) return;
     *     inorder(root.left, nums);
     *     nums.add(root.val);
     *     inorder(root.right, nums);
     *   }
     *
     *   public int[] findTwoSwapped(List<Integer> nums) {
     *     int n = nums.size();
     *     int x = -1, y = -1;
     *     for(int i = 0; i < n - 1; ++i) {
     *       if (nums.get(i + 1) < nums.get(i)) {
     *         y = nums.get(i + 1);
     *         // first swap occurence
     *         if (x == -1) x = nums.get(i);
     *         // second swap occurence
     *         else break;
     *       }
     *     }
     *     return new int[]{x, y};
     *   }
     *
     *   public void recover(TreeNode r, int count, int x, int y) {
     *     if (r != null) {
     *       if (r.val == x || r.val == y) {
     *         r.val = r.val == x ? y : x;
     *         if (--count == 0) return;
     *       }
     *       recover(r.left, count, x, y);
     *       recover(r.right, count, x, y);
     *     }
     *   }
     *
     *   public void recoverTree(TreeNode root) {
     *     List<Integer> nums = new ArrayList();
     *     inorder(root, nums);
     *     int[] swapped = findTwoSwapped(nums);
     *     recover(root, 2, swapped[0], swapped[1]);
     *   }
     * }
     */


    /**
     * 遍历的方式去
     * class Solution {
     *   public void swap(TreeNode a, TreeNode b) {
     *     int tmp = a.val;
     *     a.val = b.val;
     *     b.val = tmp;
     *   }
     *
     *   public void recoverTree(TreeNode root) {
     *     Deque<TreeNode> stack = new ArrayDeque();
     *     TreeNode x = null, y = null, pred = null;
     *
     *     while (!stack.isEmpty() || root != null) {
     *       while (root != null) {
     *         stack.add(root);
     *         root = root.left;
     *       }
     *       root = stack.removeLast();
     *       if (pred != null && root.val < pred.val) {
     *         y = root;
     *         if (x == null) x = pred;
     *         else break;
     *       }
     *       pred = root;
     *       root = root.right;
     *     }
     *
     *     swap(x, y);
     *   }
     * }
     */

}
