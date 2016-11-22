package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 *
 * Created by haitian.gan on 2016/11/18.
 */
public class MinimumHeightTrees {

    private class TreeNode{
        private int value;
        private Set<TreeNode> neighbours = new HashSet<>();

        private TreeNode(int value){
            this.value = value;
        }
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer,TreeNode> nodeMap = new HashMap<>();
        for(int i = 0;i < edges.length;i ++){
            int[] edge = edges[i];

            TreeNode node1 = nodeMap.get(edge[0]);
            if(node1 == null) {
                node1 = new TreeNode(edge[0]);
            }

            TreeNode node2 = nodeMap.get(edge[1]);
            if(node2 == null) {
                node2 = new TreeNode(edge[1]);
            }

            node1.neighbours.add(node2);
            node2.neighbours.add(node1);
        }
        return null;
    }

}
