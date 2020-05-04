package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations:
 * get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it
 * should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when
 * there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 *
 * Note that the number of times an item is used is the number of calls to the get and put functions for that item since
 * it was inserted. This number is set to zero when the item is removed.
 *
 *
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 *
 *
 * Example:
 *
 * LFUCache cache = new LFUCache( 2);
        *
        *cache.put(1,1);
        *cache.put(2,2);
        *cache.get(1);       // returns 1
        *cache.put(3,3);    // evicts key 2
        *cache.get(2);       // returns -1 (not found)
        *cache.get(3);       // returns 3.
        *cache.put(4,4);    // evicts key 1.
        *cache.get(1);       // returns -1 (not found)
        *cache.get(3);       // returns 3
        *cache.get(4);       // returns 4
 */
public class LFUCache {

    private static class Node {
        private int key;
        private int val;
        private long recentlyTime;
        private int useTimes;

        Node(int key, int val){
            this.key = key;
            this.val = val;
            this.recentlyTime = System.nanoTime();
        }

        void addUseTimes(){
            useTimes = useTimes + 1;
            recentlyTime = System.nanoTime();
        }
    }

    private long startTime;
    private int capacity;
    private PriorityQueue<Node> queue = new PriorityQueue<>((node1, node2) -> {
        int res1 = Integer.compare(node1.useTimes, node2.useTimes);
        if(res1 == 0){
            return Long.compare(node1.recentlyTime - startTime, node2.recentlyTime - startTime);
        }else{
            return res1;
        }
    });

    // 存储结点的map
    private Map<Integer, Node> map = new HashMap<>();

    /**
     * Your LFUCache object will be instantiated and called as such:
     * LFUCache obj = new LFUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.startTime = System.nanoTime();
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node == null){
            return -1;
        }

        queue.remove(node);
        node.addUseTimes();
        queue.add(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node newNode = new Node(key, value);
        Node oldNode = map.get(key);
        if(oldNode != null){
            queue.remove(oldNode);
        }

        boolean canAdd = true;
        // 先移走一个旧的，再放新的
        // 只有新加入的情况，才需要检查是不是满了
        if(oldNode == null && queue.size() >= this.capacity){
            Node removeNode = queue.poll();
            if(removeNode != null) {
                map.remove(removeNode.key);
            }else{
                canAdd = false;
            }
        }

        if(canAdd){
            queue.add(newNode);
            map.put(key, newNode);
        }
    }

    public static void main(String[] args){
        String[] op = {"put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
        int[][] arg = {{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};
        Integer[] expected = {null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,-1,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null};
        int getNum = 0;
        LFUCache lfuCache = new LFUCache(10);
        for (int i = 0; i < op.length; i++) {
            if (op[i].equals("put")) {
                lfuCache.put(arg[i][0], arg[i][1]);
            } else if (op[i].equals("get")) {
                getNum ++;
                if(getNum == 22){
                    System.out.println();
                }

                int result = lfuCache.get(arg[i][0]);
                if(result != expected[i]){
                    System.out.println("wrong!expected:" + expected[i] + ",actual:" + result + ",input:" + arg[i][0]);
                }
                System.out.println(result);
            }
        }
    }
}
