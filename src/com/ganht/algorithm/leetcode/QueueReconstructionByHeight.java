package com.ganht.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person who have a height greater
 * than or equal to h. Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 *
 * Example
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * @author haitian.gan
 */
public class QueueReconstructionByHeight {

    /**
     * 唉，其实思路都是对的，就差临门一脚
     * 最开始分析，是像构建堆一样，但碰到的问题是需要反复比较
     * 所以希望有一种结构，可以记下临时的状态。减少反复比较的次数
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        /*class Pair{
            private int height;
            private int tallerNum;

            public Pair(int height,int tallerNum){
                this.height = height;
                this.tallerNum = tallerNum;
            }
        }*/

        //pick up the tallest guy first
        //when insert the next tall guy, just need to insert him into kth position
        //repeat until all people are inserted into list

        Arrays.sort(people,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0]!=o2[0]?-o1[0]+o2[0]:o1[1]-o2[1];
            }
        });

        // java 8 lambda
        // Arrays.sort(people, (a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);

        List<int[]> res = new LinkedList<>();
        for(int[] cur : people){
            res.add(cur[1],cur);
        }
        return res.toArray(new int[people.length][]);

        //return null;
    }

}