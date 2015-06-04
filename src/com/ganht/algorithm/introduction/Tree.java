package com.ganht.algorithm.introduction;

import java.util.HashMap;
import java.util.Map;

/**
 * 树相关
 * Created by ganhaitian on 2015/6/4.
 */
public class Tree {

    public static class Node {
        public Node left;
        public Node right;
        public int id;
        public Map<String, Object> attach = new HashMap<String, Object>();
    }

    /**
     * 在二叉树中，找到距离最远的两个节点的距离
     * 思路：递归遍历所有结点，求这个结点左子树的距离根最远的距离
     * 与这个结点右子树的距离根最远的距离的和。然后反复比较，
     * 记录下最大的这个值
     */
    public int searchFarthestDistance(Node root) {
        Integer[] minParam = new Integer[]{0};
        getLongestPath(root, minParam);
        return minParam[0];
    }

    public int getLongestPath(Node node, Integer[] param) {

        if (node == null)
            return -1;

        Node left = node.left;
        Node right = node.right;

        // 左边最长的距离
        int leftLongestPath = getLongestPath(left, param) + 1;
        // 右边最长的距离
        int rightLongestPath = getLongestPath(right, param) + 1;

        int maxDistance = leftLongestPath + rightLongestPath;
        if (maxDistance >= param[0]) {
            param[0] = maxDistance;
        }

        int pathLength = Math.max(leftLongestPath, rightLongestPath);
        return pathLength;
    }

    public static void main(String[] args) {

        Node top = new Node();
        top.id = 0;
        Node _1 = new Node();
        _1.id = 1;
        Node _2 = new Node();
        _2.id = 2;
        top.left = _1;
        top.right = _2;

        Node _3 = new Node();
        _3.id = 3;
        Node _4 = new Node();
        _4.id = 4;
        _1.left = _3;
        _1.right = _4;

        Node _5 = new Node();
        _5.id = 5;
        Node _6 = new Node();
        _6.id = 6;
        _2.left = _5;
        _2.right = _6;

        Node _7 = new Node();
        _7.id = 7;
        _5.left = _7;

        System.out.println(new Tree().searchFarthestDistance(top));
    }

}
