package com.ganht.algorithm.introduction;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        private List<GraphNode> adjacents;
        private State state = State.Unvisited;// 访问状态
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
    public boolean findRoute(GraphNode start,GraphNode end){
        if(start == null || end == null)
            return false;

        Queue<GraphNode> tmpBuff = new LinkedList<GraphNode>();
        tmpBuff.add(start);

        while(tmpBuff.size() > 0){
            GraphNode currNode = tmpBuff.poll();
            for(GraphNode adjacent: currNode.adjacents){
                if(adjacent == end){
                    return true;
                }
                else {
                    tmpBuff.add(adjacent);
                    adjacent.state = State.Visiting;
                }
            }
            currNode.state = State.Visited;
        }

        return true;
    }

    /**
     * Given a sorted (increasing order) array with unique
     * integer elements, write an algorithm to create a
     * binary search tree with minimal height.
     */
    public void buildMinHeightBinSearchTree(int[] array){
        // 想要构建高度最小的二叉树，关键是要让所有的结点满足一个条件
        // 那就是，尽可能让左子树的结点数目和右子树的结点数目一样多。
        // 再结合BST的特点，那就是让中间大的数字成为根结点
        // 遵循这个规律，递归的调用就好了
        buildMinimalBST(array,0,array.length - 1);
    }

    private BinNode buildMinimalBST(int[] array,int start,int end){

        if(end < start){
            return null;
        }

        int mid = ( start + end )/2;
        int midValue = array[mid];

        BinNode root = new BinNode(midValue);
        root.left = buildMinimalBST(array,start,mid - 1);
        root.right = buildMinimalBST(array,mid + 1,end);
        return root;
    }

    /**
     * Given a binary tree, design an algorithm which creates a linked
     * list of all the nodes at each depth (e.g., if you have a tree
     * with depth D,you'll have D linked lists).
     */
    public void createLinkedListOfTree(){

    }


    public static void main(String[] args){
       // new TreesAndGraphs().checkTreeBalanced();
    }

}
