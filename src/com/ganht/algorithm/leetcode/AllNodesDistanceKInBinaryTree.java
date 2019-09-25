package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 *
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in
 * any order.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 *
 * Output: [7,4,1]
 *
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 *
 *
 *
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 *
 *
 * Note:
 *
 * The given tree is non-empty.
 * Each node in the tree has unique values 0 <= node.val <= 500.
 * The target node is a node in the tree.
 * 0 <= K <= 1000.
 * @author haitian.gan
 */
public class AllNodesDistanceKInBinaryTree {

    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public String toString(){
            return String.valueOf(val);
        }
    }

    private Map<TreeNode, Integer>  distanceMap  = new HashMap<>();
    private List<TreeNode>          unknownNodes = new ArrayList<>();
    private Map<TreeNode, TreeNode> inheritMap   = new HashMap<>();
    private List<Integer>           result       = new ArrayList<>();

    // 想得太复杂了，其实可以把他看成一个图，而parent只是其中的一条边，只要构建成父子级关系就可以了
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        distanceMap.put(target, 0);
        calDistance(null, root, target, K);

        for (TreeNode node : unknownNodes) {
            int distance = calUnknownNode(node);
            if(distance == K){
                result.add(node.val);
            }
        }

        return result;
    }

    private int calUnknownNode(TreeNode node) {
        TreeNode parent         = inheritMap.get(node);
        Integer  parentDistance = distanceMap.get(parent);
        if (parentDistance == null) {
            return calUnknownNode(parent) + 1;
        } else {
            return parentDistance + 1;
        }
    }

    public int calDistance(TreeNode parent, TreeNode node, TreeNode target, int K) {
        if (node == null) {
            return -1;
        }

        int distance = -1;
        if (node.val == target.val) {
            distance = 0;
        }

        Integer parentDistance = distanceMap.get(parent);
        if (parentDistance != null) {
            distance = parentDistance + 1;
            distanceMap.put(node, distance);
        }

        int leftDistance = calDistance(node, node.left, target, K);
        if (leftDistance != -1 && distance == -1) {
            distance = leftDistance + 1;
            distanceMap.put(node, distance);
        }

        int rightDistance = calDistance(node, node.right, target, K);
        if (rightDistance != -1 && distance == -1) {
            distance = rightDistance + 1;
            distanceMap.put(node, distance);
        }

        if (distance == -1) {
            unknownNodes.add(node);
            inheritMap.put(node, parent);
        } else if (distance == K) {
            result.add(node.val);
        }

        return distance;
    }

    Map<TreeNode, TreeNode> parent;
    public List<Integer> distanceK1(TreeNode root, TreeNode target, int K) {
        parent = new HashMap();
        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(null);
        queue.add(target);

        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n: queue)
                        ans.add(n.val);
                    return ans;
                }
                queue.offer(null);
                dist++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    public static void main(String[] args){
        TreeNode _0node = new TreeNode(0);
        TreeNode _1node = new TreeNode(1);
        TreeNode _2node = new TreeNode(2);
        TreeNode _3node = new TreeNode(3);
        TreeNode _4node = new TreeNode(4);
        TreeNode _5node = new TreeNode(5);
        TreeNode _6node = new TreeNode(6);
        TreeNode _7node = new TreeNode(7);
        TreeNode _8node = new TreeNode(8);

        _3node.left = _5node;
        _3node.right = _1node;
        _5node.left = _6node;
        _5node.right = _2node;
        _1node.left = _0node;
        _1node.right = _8node;
        _2node.left = _7node;
        _2node.right = _4node;

        /*TreeNode _0node = new TreeNode(0);
        TreeNode _1node = new TreeNode(1);
        TreeNode _2node = new TreeNode(2);
        TreeNode _3node = new TreeNode(3);
        _0node.left = _1node;
        _1node.left = _3node;
        _1node.right = _2node;*/

        List<Integer> result = new AllNodesDistanceKInBinaryTree().distanceK1(_3node, _5node, 2);
        System.out.println(result);
    }

}
