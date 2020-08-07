package com.ganht.algorithm.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where
 * counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * @author haitian.gan
 */
public class CountOfSmallerNumbersAfterSelf {

    private static class Node{
        private int key,height,lessNum;
        private Node left;
        private Node right;

        private Node(int key){
            this.key = key;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        AVLTree avlTree = new AVLTree();
        LinkedList<Integer> result = new LinkedList<>();
        for (int index = nums.length - 1; index >= 0; index--) {
            avlTree.insert(avlTree.root, nums[index]);

        }

        return result;
    }

    class AVLTree {

        Node root;

        // A utility function to get the height of the tree
        int height(Node N) {
            if (N == null)
                return 0;

            return N.height;
        }

        // A utility function to get maximum of two integers
        int max(int a, int b) {
            return (a > b) ? a : b;
        }

        // A utility function to right rotate subtree rooted with y
        // See the diagram given above.
        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            // Perform rotation
            x.right = y;
            y.left = T2;

            // Update heights
            y.height = max(height(y.left), height(y.right)) + 1;
            x.height = max(height(x.left), height(x.right)) + 1;

            // Return new root
            return x;
        }

        // A utility function to left rotate subtree rooted with x
        // See the diagram given above.
        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            // Perform rotation
            y.left = x;
            x.right = T2;

            //  Update heights
            x.height = max(height(x.left), height(x.right)) + 1;
            y.height = max(height(y.left), height(y.right)) + 1;

            // Return new root
            return y;
        }

        // Get Balance factor of node N
        int getBalance(Node N) {
            if (N == null)
                return 0;

            return height(N.left) - height(N.right);
        }

        Node insert(Node node, int key) {

            /* 1.  Perform the normal BST insertion */
            if (node == null)
                return (new Node(key));

            if (key < node.key)
                node.left = insert(node.left, key);
            else if (key > node.key)
                node.right = insert(node.right, key);
            else // Duplicate keys not allowed
                return node;

            /* 2. Update height of this ancestor node */
            node.height = 1 + max(height(node.left),
                    height(node.right));

            /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
            int balance = getBalance(node);

            // If this node becomes unbalanced, then there
            // are 4 cases Left Left Case
            if (balance > 1 && key < node.left.key)
                return rightRotate(node);

            // Right Right Case
            if (balance < -1 && key > node.right.key)
                return leftRotate(node);

            // Left Right Case
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // Right Left Case
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            /* return the (unchanged) node pointer */
            return node;
        }

        /*public static void main(String[] args) {
            AVLTree tree = new AVLTree();

            *//* Constructing tree given in the above figure *//*
            tree.root = tree.insert(tree.root, 10);
            tree.root = tree.insert(tree.root, 20);
            tree.root = tree.insert(tree.root, 30);
            tree.root = tree.insert(tree.root, 40);
            tree.root = tree.insert(tree.root, 50);
            tree.root = tree.insert(tree.root, 25);

        *//* The constructed AVL Tree would be
             30
            /  \
          20   40
         /  \     \
        10  25    50
        *//*
            System.out.println("Preorder traversal" +
                    " of constructed tree is : ");
            tree.preOrder(tree.root);
        }*/
    }

    /*private int addToTree(TreeNode root, TreeNode newNode){
        TreeNode curr = root;
        int count = 0;
        while(true){
            if(newNode.val > curr.val){
                count ++;
                if(curr.right == null){
                    curr.right = newNode;
                    break;
                }else{
                    curr = curr.right;
                }
            }else{
                if(curr.left == null){
                    curr.left = newNode;
                    break;
                }else{
                    curr = curr.left;
                }
            }
        }

        return count;
    }*/

    public static void main(String[] args){

    }

}
