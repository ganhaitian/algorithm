package com.ganht.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations:
 * get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it
 * should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache( 2  capacity  );
        *
        *cache.put(1,1);
        *cache.put(2,2);
        *cache.get(1);       // returns 1
        *cache.put(3,3);    // evicts key 2
        *cache.get(2);       // returns -1 (not found)
        *cache.put(4,4);    // evicts key 1
        *cache.get(1);       // returns -1 (not found)
        *cache.get(3);       // returns 3
        *cache.get(4);       // returns 4
 * @author haitian.gan
 */
public class LRUCache {

    // 妈蛋的，真是......小瞧LinkedHashMap了
    private static class CacheObj implements Comparable<CacheObj>{
        private int  key;
        private long timestamp;

        CacheObj(int key, long timestamp){
            this.key = key;
            this.timestamp = timestamp;
        }

        @Override
        public int compareTo(CacheObj o) {
            return Long.compare(timestamp, o.timestamp);
        }

        @Override
        public boolean equals(Object o){
            CacheObj t = (CacheObj)o;
            return t.key == this.key;
        }
    }

    private long                    timestamp;
    private int                     capacity;
    private PriorityQueue<CacheObj> cacheExpireSet;
    private Map<Integer, Integer>   cache;

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        this.capacity = capacity;
        cacheExpireSet = new PriorityQueue<>();
    }

    public int get(int key) {
        Integer value = cache.get(key);
        if (value == null) {
            return -1;
        }

        // 加到过期列表里面
        CacheObj cacheObj = new CacheObj(key, timestamp++);
        cacheExpireSet.remove(cacheObj);
        cacheExpireSet.add(cacheObj);
        return value;
    }

    public void put(int key, int value) {
        cache.put(key, value);

        // 加到过期列表里面
        CacheObj cacheObj = new CacheObj(key, timestamp++);
        cacheExpireSet.remove(cacheObj);
        cacheExpireSet.add(cacheObj);

        if (cache.size() > this.capacity) {
            CacheObj expireObj = cacheExpireSet.poll();
            if (expireObj != null) {
                cache.remove(expireObj.key);
            }
        }
    }

    public static void main(String[] args){
        String[] op = {"put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
        int[][] arg = {{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};

        LRUCache lruCache = new LRUCache(10);
        for (int i = 0; i < op.length; i++) {
            if (op[i].equals("put")) {
                lruCache.put(arg[i][0], arg[i][1]);
            } else if (op[i].equals("get")) {
                if(arg[i][0] == 6){
                    System.out.println(1);
                }

                lruCache.get(arg[i][0]);
            }
        }

    }

}
