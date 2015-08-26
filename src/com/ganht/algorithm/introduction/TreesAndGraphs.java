package com.ganht.algorithm.introduction;

/**
 * 树和图
 * Created by ganhaitian on 15/8/25.
 */
public class TreesAndGraphs{

    public class BinNode{

        public BinNode left;
        public BinNode right;
        private Object data;
        private int height;

        public BinNode(Object data){
            this.data = data;
        }

    }

    public class GraphNode{

    }

    /**
     * Implement a function to check if a binary tree is balanced.
     * For the purposes of this question, a balanced tree is defined
     * to be a tree such that the heights of the two subtrees of
     * any node never differ by more than one.
     */
    public int checkHeight(BinNode node){

        // 我这种解决方案，没大毛病，只是需要treenode添加属性
        // 这种方案的好处就是，从树的底部往上追溯，可以减少递归次数
        // 更优的解决办法，是不返回bool类型，而是返回高度，而用-1代表非平衡状态，这样一箭双雕
        if(node == null)
            return 0;

        int leftHeight = checkHeight(node.left);
        if(leftHeight == -1)
            return -1;

        int rightHeight = checkHeight(node.right);
        if(rightHeight == -1)
            return -1;

        if(Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }else
            return Math.max(leftHeight,rightHeight) + 1;

    }

    public enum State{
        Unvisited,Visited,Visiting;
    }


    /**
     * Given a directed graph, design an algorithm to find out whether
     * there is a route between two nodes.
     */
    public void findRoute(){

    }

    public static void main(String[] args){
       // new TreesAndGraphs().checkTreeBalanced();
    }

}
