package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class PoplulatingNextRightPointersInEachNode {

    public static class TreeLinkNode {
      int val;
      TreeLinkNode left, right, next;
      TreeLinkNode(int x) { val = x; }
    }

    public void connect(TreeLinkNode root) {
        if(root == null || root.left == null || root.right == null)
            return;

        root.next = null;
        root.left.next = root.right;
        root.right.next = null;

        doConnect(root.left);
        doConnect(root.right);
    }

    private void doConnect(TreeLinkNode node) {
        if (node == null || node.left == null || node.right == null)
            return;

        node.left.next = node.right;
        node.right.next = node.next == null ? null : node.next.left;

        doConnect(node.left);
        doConnect(node.right);
    }

    public static void main(String[] args){
        int[] input = {0,1,2,3,4,5,6};
        Map<Integer, TreeLinkNode> nodeMap = new HashMap<>();
        TreeLinkNode root = null;
        for(int i = 0;i < input.length;i ++){
            TreeLinkNode node = nodeMap.getOrDefault(i,new TreeLinkNode(input[i]));
            if(i == 0)
                root = node;

            nodeMap.put(i,node);

            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if(left >= input.length || right >= input.length)
                continue;

            node.left = new TreeLinkNode(input[left]);
            node.right = new TreeLinkNode(input[right]);

            nodeMap.put(input[left],node.left);
            nodeMap.put(input[right],node.right);
        }

        new PoplulatingNextRightPointersInEachNode().connect(root);
    }

}
