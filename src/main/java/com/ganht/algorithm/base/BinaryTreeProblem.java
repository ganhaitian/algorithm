package com.ganht.algorithm.base;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * 二叉树类型的问题
 */
public abstract class BinaryTreeProblem {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static String rserialize(TreeNode root, String str) {
        // Recursive serialization.
        if (root == null) {
            str += "null,";
        } else {
            str += root.val + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    private static TreeNode rdeserialize(List<String> l) {
        // Recursive deserialization.
        if (l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        var data_array = data.split(",");
        var data_list  = new LinkedList<>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }

    /**
     * 从数组中构建一颗二叉树
     * 这是之前自己瞎琢磨的，后来发现不对
     * @param input
     * @return
     */
    @Deprecated
    protected static TreeNode buildTreeFromArray(Integer[] input){
        if(input == null){
            return null;
        }

        TreeNode[] nodes = new TreeNode[input.length];
        Function<Integer, TreeNode> initTreeNodeFunc = index -> {
            if (index < input.length) {
                Integer val = input[index];
                if (val != null) {
                    nodes[index] = new TreeNode(val);
                    return nodes[index];
                }
            }

            return null;
        };

        TreeNode root = null;
        int leftIndex,rightIndex;
        for (int i = 0; i < input.length; i++) {
            Integer val = input[i];
            if (val == null) {
                continue;
            }

            TreeNode node = nodes[i] == null ? new TreeNode(val) : nodes[i];
            if(i == 0){
                root = node;
            }

            leftIndex = (i * 2) + 1;
            node.left = initTreeNodeFunc.apply(leftIndex);

            rightIndex = leftIndex + 1;
            node.right = initTreeNodeFunc.apply(rightIndex);
        }

        return root;
    }

}
