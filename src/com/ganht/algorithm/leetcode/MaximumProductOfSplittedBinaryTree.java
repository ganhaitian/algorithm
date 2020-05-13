package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.base.BinaryTreeProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the sums
 * of the subtrees are maximized.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
 * Example 2:
 *
 *
 *
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation:  Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 * Example 3:
 *
 * Input: root = [2,3,9,10,7,8,6,5,4,11,1]
 * Output: 1025
 * Example 4:
 *
 * Input: root = [1,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * Each tree has at most 50000 nodes and at least 2 nodes.
 * Each node's value is between [1, 10000].
 * @author haitian.gan
 */
public class MaximumProductOfSplittedBinaryTree extends BinaryTreeProblem {

    private Map<TreeNode, Integer> cache = new HashMap<>();
    private int maxProduct = 0;
    private int totalSum = 0;


    private List<Integer> allSums = new ArrayList<>();

    // 思路是对的，差他妈的最后一步
    public int maxProduct1(TreeNode root) {
        // long is a 64-bit integer.
        long totalSum = treeSum(root);
        long best = 0;
        for (long sum : allSums) {
            best = Math.max(best, sum * (totalSum - sum));
        }
        // We have to cast back to an int to match return value.
        return (int)(best % 1000000007);

    }

    private int treeSum(TreeNode subroot) {
        if (subroot == null) return 0;
        int leftSum = treeSum(subroot.left);
        int rightSum = treeSum(subroot.right);
        int totalSum = leftSum + rightSum + subroot.val;
        allSums.add(totalSum);
        return totalSum;
    }

    public int maxProduct(TreeNode root) {
        calSum(root);
        this.totalSum = cache.get(root);

        traverse(root);
        return maxProduct;
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }

        traverse(node.left);
        traverse(node.right);

        Integer sum1 = cache.get(node);
        int     sum  = sum1 * (this.totalSum - sum1);
        if (sum > maxProduct) {
            maxProduct = sum;
        }
    }

    private int calSum(TreeNode node){
        if(node == null){
            return 0;
        }

        int sum = node.val + calSum(node.left) + calSum(node.right);
        cache.put(node, sum);
        return sum;
    }

    public static void main(String[] args){
        Integer[] input = {1,null,2,3,4,null,null,5,6};
        new MaximumProductOfSplittedBinaryTree().maxProduct(buildTreeFromArray(input));
    }

}
