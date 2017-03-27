package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 * Created by haitian.gan on 2017/2/24.
 */
public class TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> frequentMap = new HashMap<>();
        HashMap<Integer,List<Integer>> frequentRank = new LinkedHashMap<>();
        int fNum;
        for(int num:nums){
            if(!frequentMap.containsKey(num)){
                frequentMap.put(num,1);
            }else
                frequentMap.put(num,frequentMap.get(num) + 1);

            fNum = frequentMap.get(num);
            List<Integer> lastPos = frequentRank.get(fNum - 1);
            if(lastPos != null){
                lastPos.remove((Integer)num);
            }

            List<Integer> nowPos = frequentRank.get(fNum);
            if(nowPos != null){
                nowPos.add(num);
            }else{
                List<Integer> poses = new ArrayList<>();
                poses.add(num);
                frequentRank.put(fNum,poses);
            }
        }

        Set<Integer> keys = frequentRank.keySet();
        Object[] ranks = keys.toArray();
        List<Integer> result = new ArrayList<>();

        int index = ranks.length - 1;
        while(index >= 0){
            if(result.size() >= k)
                break;

            if(frequentRank.get(ranks[index]).isEmpty()){
                index--;
                continue;
            }

            result.addAll(frequentRank.get(ranks[index]));
            index--;
        }

        return result;
    }

    public static void main(String[] args){
        System.out.println(new Date(1488522584124L));
    }

}
