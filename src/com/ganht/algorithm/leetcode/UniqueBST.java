package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

    For example,
    Given n = 3, there are a total of 5 unique BST's.

    这道题有两个关键的地方，首先要总结出。对于递增的数列来讲，unique bst的结果，和数列的内容无关，只和数列的长度有关。
    即1,2,3,4和5，6，7，8的结果是一样的。因为搜索二叉数是通过相对的大小关系，来组织树的结构。

    第二个关键的地方就是，第一次提交代码报了Time limit exceeded的错误，这个是指超时了。优化的方案很简单，存储中间结果就可以了。
    避免重复的递归运算。
 *  Created by ganhaitian on 16/7/8.
 */
public class UniqueBST {

    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value){
            this.value = value;
        }
    }

    private static Map<Integer,Integer> resultCache = new HashMap<Integer,Integer>();

    public static int calUniqueBSTNums(int n){

        if(n == 0)
            return 0;
        else if(n == 1)
            return 1;
        else if(n == 2)
            return 2;

        if(resultCache.containsKey(n))
            return resultCache.get(n);

//        List<TreeNode> nodes = new ArrayList();
//        for(int i =0;i < n;i++){
//            nodes.add(new TreeNode(n));
//        }

        int total = 0;
        for(int j =0;j <n;j++){
            /*List<TreeNode> copyNodes = new ArrayList<TreeNode>();
            recursiveConstructBST(nodes.get(j - 1),copyNodes);*/
            total += recursiveConstructBST1(j,n);
        }

        resultCache.put(n,total);
        return total;
    }

    public static int recursiveConstructBST1(int index,int length){
        int leftParsNums = 0;
        if(index == 0)
            leftParsNums = 1;
        else
            leftParsNums = calUniqueBSTNums(index);

        int rightPartsNums = 0;
        if(index == length - 1)
            rightPartsNums = 1;
        else
            rightPartsNums = calUniqueBSTNums(length - index - 1);

        return leftParsNums * rightPartsNums;
    }

    public static int recursiveConstructBST(TreeNode curNode,List<TreeNode> nodes){

        if(nodes.size() == 0)
            return 1;

        // 计算当算根结点所处的索引位置
        int index = nodes.indexOf(curNode);
        int leftPartsNum = calUniqueBSTNums(index);

        int rightPartsNum = calUniqueBSTNums(nodes.size() - index - 1);
        int total = leftPartsNum * rightPartsNum;

        return total;
    }

    public static void main(String[] args){
        long begin = System.currentTimeMillis();
        System.out.println(calUniqueBSTNums(19));
        System.out.println(System.currentTimeMillis() - begin);
    }

}
