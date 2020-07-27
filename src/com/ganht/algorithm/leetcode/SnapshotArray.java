package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * Implement a SnapshotArray that supports the following interface:
 * <p>
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= length <= 50000
 * At most 50000 calls will be made to set, snap, and get.
 * 0 <= index < length
 * 0 <= snap_id < (the total number of times we call snap())
 * 0 <= val <= 10^9
 */
public class SnapshotArray {

/*    private Map<Integer, int[]> history = new HashMap<>();
    private int currId = 0;
    private int length = 0;

    public SnapshotArray(int length) {
        this.length = length;
        history.put(0, new int[length]);
    }

    public void set(int index, int val) {
        history.get(currId)[index] = val;
    }

    public int snap() {
        int old = currId++;
        int[] newArray = new int[this.length];
        System.arraycopy(history.get(old),0,newArray,0,this.length);
        history.put(currId, newArray);
        return old;
    }

    public int get(int index, int snap_id) {
        return history.get(snap_id)[index];
    }*/

    // 思路特别清晰，就是有点儿弯弯绕
    private TreeMap<Integer, Integer>[] array;

    private int currId = 0;

    public SnapshotArray(int length) {
        array = new TreeMap[length];
    }

    public void set(int index, int val) {
        Optional.ofNullable(array[index])
                .orElseGet(() -> array[index] = new TreeMap<>())
                .put(currId, val);
    }

    public int snap() {
        return currId++;
    }

    public int get(int index, int snap_id) {
        return Optional.ofNullable(array[index])
                .map(m -> m.floorEntry(snap_id))
                .map(v -> v.getValue())
                .orElse(0);
    }

}
