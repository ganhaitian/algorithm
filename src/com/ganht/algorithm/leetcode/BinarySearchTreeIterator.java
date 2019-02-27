package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * Example:
 * <p>
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // return 3
 * iterator.next();    // return 7
 * iterator.hasNext(); // return true
 * iterator.next();    // return 9
 * iterator.hasNext(); // return true
 * iterator.next();    // return 15
 * iterator.hasNext(); // return true
 * iterator.next();    // return 20
 * iterator.hasNext(); // return false
 * <p>
 * Note:
 * <p>
 * next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the
 * BST when next() is called.
 *
 * @author haitian.gan
 */
public class BinarySearchTreeIterator {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class BSTIterator {

        private LinkedList<Integer> minQueue = new LinkedList<>();

        // 这里面做一个递归
        public BSTIterator(TreeNode root) {
            // minStack.push(root);
            // TreeNode currNode = root;

            findMinNode(root);

            /*while(true){
                TreeNode leftNode = currNode.left;
                if(leftNode != null){
                    if(leftNode.left == null || leftNode.right == null){
                        minQueue.addFirst(leftNode.val);
                        minStack.pop();

                        if(currNode.right != null){
                            minStack.push(currNode.right);
                        }else{

                        }
                    }else{
                        minStack.push(leftNode);
                        currNode = leftNode;
                        continue;
                    }
                }
            }*/
        }

        private void findMinNode(TreeNode currNode) {
            if (currNode == null) {
                return;
            }

            // 如果没有左子树，而右子数的所有点肯定会比当前的大
            // 则当前的结点一定要先加进来
            if (currNode.left != null) {
                findMinNode(currNode.left);
                minQueue.addFirst(currNode.val);
            }

            if (currNode.right != null) {
                if(currNode.left == null){
                    minQueue.addFirst(currNode.val);
                }

                findMinNode(currNode.right);
            }

            if (currNode.left == null && currNode.right == null) {
                minQueue.addFirst(currNode.val);
            }
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            return minQueue.pollLast();
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return minQueue.size() > 0;
        }
    }

    public static void main(String[] args) {
        /*TreeNode root = new TreeNode(7);
        TreeNode node3 = new TreeNode(3);
        root.left = node3;

        TreeNode node15 = new TreeNode(15);
        root.right = node15;

        TreeNode node9 = new TreeNode(9);
        node15.left = node9;

        TreeNode node20 = new TreeNode(20);
        node15.right = node20;*/

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        root.right = node2;

        BSTIterator iterator = new BSTIterator(root);
        System.out.println(iterator.next());    // return 3
        System.out.println(iterator.next());    // return 7
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 9
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 15
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 20
        System.out.println(iterator.hasNext()); // return false
    }

}
