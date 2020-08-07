package com.ganht.algorithm.leetcode;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 * <p>
 * Basically, the deletion can be divided into two stages:
 * <p>
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 * <p>
 * Example:
 * <p>
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * <p>
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * <p>
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 * <p>
 * 5
 * / \
 * 4   6
 * /     \
 * 2       7
 * <p>
 * Another valid answer is [5,2,6,null,4,null,7].
 * <p>
 * 5
 * / \
 * 2   6
 * \   \
 * 4   7
 * Created by ganhaitian on 2017/3/4.
 */
public class DeleteNodeInBst {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        return null;
    }

    private void findAndRemoveNode(TreeNode node,TreeNode parent,int target){
        if(node.val == target){
            if(isLeftChildNode(parent,node)){
                if(node.left != null){
                    parent.left = node.left;
                    addNode(node.left,node.right);
                }else if(node.right != null){
                    parent.left = node.right;
                }
            }else{
                if(node.left != null){
                    parent.right = node.left;
                    addNode(node.left,node.right);
                }else if(node.right != null){
                    parent.right = node.right;
                }
            }
        }else if(node.val > target){
            findAndRemoveNode(node.left,node,target);
        }else if(node.val < target){
            findAndRemoveNode(node.right,node,target);
        }
    }

    private boolean isLeftChildNode(TreeNode parent,TreeNode childNode){
        if(parent.left != null && parent.left.val == childNode.val){
            return true;
        }else
            return false;
    }

    private void addNode(TreeNode root,TreeNode newNode){
        TreeNode tmpNode = root;
        while(true){
            if(tmpNode.left.val < newNode.val)
                tmpNode = tmpNode.right;
            else
                tmpNode = tmpNode.left;

        }
    }

}
