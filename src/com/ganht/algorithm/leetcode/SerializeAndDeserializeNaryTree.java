package com.ganht.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Function;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
 * in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or
 * another computer environment.
 *
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has
 * no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You
 * just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original
 * tree structure.
 *
 * For example, you may serialize the following 3-ary tree
 *
 *
 *
 * as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.
 *
 * Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by
 * the null value.
 *
 *
 *
 * For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
 *
 * You do not necessarily need to follow the above suggested formats, there are many more different formats that work so
 * please be creative and come up with different approaches yourself.
 *
 *
 *
 * Constraints:
 *
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 * @author haitian.gan
 */
public class SerializeAndDeserializeNaryTree {

    static class Node {
        public int        val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    class Codec {

        private class Node1 {
            public Node node;
            public int  childNum;

            public Node1(Node node, int num) {
                this.node     = node;
                this.childNum = num;
            }

            public int getActualChildNum(){
                return Optional.ofNullable(this.node.children).map(List::size).orElse(0);
            }
        }

        // Encodes a tree to a single string.
        public String serialize(Node root) {
            if(root == null){
                return null;
            }

            var strList = new ArrayList<String>();
            dfs(root, strList);
            return String.join(",", strList);
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if(data == null){
                return null;
            }

            var nodeStrArray = data.split(",");
            var stack        = new Stack<Node1>();

            Function<String, Node1> parseFunc = str -> {
                var parts = str.split(":");
                var _n    = new Node(Integer.parseInt(parts[0]), new ArrayList<>());
                return new Node1(_n, Integer.parseInt(parts[1]));
            };

            int index = 1;
            var root  = parseFunc.apply(nodeStrArray[0]);
            stack.add(root);
            while (stack.size() > 0 && index < nodeStrArray.length) {
                var node = stack.peek();
                if (node.childNum > 0) {
                    if (node.getActualChildNum() < node.childNum) {
                        Node1 newNode = parseFunc.apply(nodeStrArray[index++]);
                        node.node.children.add(newNode.node);
                        stack.push(newNode);
                    } else {
                        stack.pop();
                    }
                } else {
                    stack.pop();
                }
            }

            return root.node;
        }


        private void dfs(Node node,List<String> values){
            if(node.children == null){
                values.add(node.val + ":" + "0");
                return;
            }

            values.add(node.val + ":" + node.children.size());
            for(Node child : node.children){
                dfs(child, values);
            }
        }
    }

    public static void main(String[] args){
        var codes = new SerializeAndDeserializeNaryTree().new Codec();
        codes.serialize(codes.deserialize("1:4,2:0,3:2,6:0,7:1,11:1,14:0,4:1,8:1,12:0,5:2,9:1,13:0,10:0"));
    }

}
