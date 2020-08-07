package com.ganht.algorithm.leetcode;

import com.ganht.algorithm.base.BinaryTreeProblem;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
 * in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or
 * another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization
 * algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be
 * deserialized to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to
 * follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should
 * be stateless.
 * @author haitian.gan
 */
public class SerializeAndDeserializeBinaryTree extends BinaryTreeProblem {


    /*public String rserialize(TreeNode root, String str) {
        // Recursive serialization.
        if (root == null) {
            str += "null,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    public TreeNode rdeserialize(List<String> l) {
        // Recursive deserialization.
        if (l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }*/

    public class Codec {

        // 感觉还是标准答案里面的DFS好些
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            class IndexedTreeNode{
                final TreeNode node;
                final int      index;

                IndexedTreeNode(TreeNode node,int index){
                    this.node = node;
                    this.index = index;
                }
            }

            if(root == null){
                return null;
            }

            //BFS
            var queue = new LinkedList<IndexedTreeNode>();
            queue.offer(new IndexedTreeNode(root, 1));

            var nodeStrList = new ArrayList<String>();
            int deep = 0;
            while (queue.size() > 0) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    IndexedTreeNode node = queue.poll();
                    int index = node.index;
                    nodeStrList.add(deep + ":" + index + ":" + node.node.val);

                    if(node.node.left != null){
                        queue.offer(new IndexedTreeNode(node.node.left,index * 2 - 1));
                    }
                    if(node.node.right != null){
                        queue.offer(new IndexedTreeNode(node.node.right, index * 2));
                    }
                }

                deep ++;
            }

            return String.join(",", nodeStrList);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == null){
                return null;
            }

            var cacheMap = new HashMap<Integer, HashMap<Integer, TreeNode>>();
            TreeNode root = null;
            String[] nodeStrArray = data.split(",");
            for (int i = nodeStrArray.length - 1; i >= 0; i--) {
                String   nodeStr = nodeStrArray[i];
                String[] parts   = nodeStr.split(":");

                // 创建结点
                TreeNode node = new TreeNode(Integer.parseInt(parts[2]));
                if (i == 0) {
                    root = node;
                }

                int deep  = Integer.parseInt(parts[0]);
                int index = Integer.parseInt(parts[1]);
                cacheMap.computeIfAbsent(deep, k -> new HashMap<>()).put(index, node);

                // 在cache里面找到左右子结点
                var childDeepMap = cacheMap.get(deep + 1);
                if(childDeepMap != null){
                    node.left  = childDeepMap.get(index * 2 - 1);
                    node.right = childDeepMap.get(index * 2);
                }
            }

            return root;
        }
    }

    public static void main(String[] args){
        var parent = new SerializeAndDeserializeBinaryTree();
        var codes = parent.new Codec();

        Integer[] array = {1,2,3,null,null,4,5};
        codes.deserialize(codes.serialize(buildTreeFromArray(array)));
    }

}
