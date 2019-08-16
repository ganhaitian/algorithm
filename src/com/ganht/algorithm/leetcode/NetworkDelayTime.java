package com.ganht.algorithm.leetcode;

import java.util.*;

/**
 * There are N network nodes, labelled 1 to N.
 * <p>
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node,
 * and w is the time it takes for a signal to travel from source to target.
 * <p>
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible,
 * return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 * <p>
 * <p>
 * Note:
 * <p>
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 *
 * @author haitian.gan
 */
public class NetworkDelayTime {

    private Map<String, Integer> cache = new HashMap<>();

    private static class Node {
        private List<Node>            neighbors;
        private int                   code;
        private Map<Integer, Integer> timeMap;

        private Node(int code) {
            this.code = code;
            this.neighbors = new ArrayList<>();
            this.timeMap = new HashMap<>();
        }

        public String toString(){
            return String.valueOf(code);
        }
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Node> nodeMap  = new HashMap<>();
        for (int[] time : times) {
            Node tmpNode = nodeMap.get(time[0]);
            if (tmpNode == null) {
                tmpNode = new Node(time[0]);
                nodeMap.put(time[0], tmpNode);
            }

            Node target = nodeMap.get(time[1]);
            if (target == null) {
                target = new Node(time[1]);
                nodeMap.put(time[1], target);
            }

            tmpNode.neighbors.add(target);
            tmpNode.timeMap.put(time[1], time[2]);
        }

        Node start = nodeMap.get(K);
        if (start == null) {
            return -1;
        }

        Set<Integer> allNodes = new HashSet<>();
        for (int n = 1; n <= N; n++) {
            if (n == K) {
                continue;
            }

            allNodes.add(n);
        }

        int result = 0;
        for (Integer targetCode : allNodes) {
            int time = search(start, targetCode, 0, new TreeSet<>());
            //cache.putIfAbsent(start.code + "-" + targetCode, time);

            if (time == -1) {
                return -1;
            }

            if (time > result) {
                result = time;
            }
        }

        return result;
    }

    private int search(Node curr, int target, int accuTime, Set<Integer> elapsed) {
        String  cacheKey    = curr.code + "-" + target + "-" + elapsed.toString();
        Integer cacheResult = cache.get(cacheKey);
        if (cacheResult != null) {
            return cacheResult;
        }

        int        result    = -1;
        List<Node> neighbors = curr.neighbors;
        for (Node neighbor : neighbors) {
            // 不能死循环
            if (elapsed.contains(neighbor.code)) {
                continue;
            }

            //String cacheKey = curr.code + "-" + neighbor.code;

            int time = curr.timeMap.get(neighbor.code);
            if (neighbor.code == target) {
                //cache.putIfAbsent(cacheKey, accuTime + time);
                result = result == -1 ? time : Math.min(time, result);
                // 不能结束，后面可能有更快的结果
                continue;
            }

            // 如果已经到结尾了
            if (neighbor.neighbors.size() == 0) {
                continue;
            }

            // 如果单次的时间就大于整体的时间，后面可以不用看了
            if(result != -1 && time > result){
                continue;
            }

            // 不能共用一个
            Set<Integer> newElapsed = new TreeSet<>(elapsed);
            newElapsed.add(curr.code);
            //Integer cacheTime    = cache.get(cacheKey);
            int followUpTime = search(neighbor, target, 0, newElapsed);
            if (followUpTime != -1) {
                followUpTime += time;
            }

            //cache.putIfAbsent(cacheKey, followUpTime);

            if (followUpTime != -1) {
                result = result == -1 ? followUpTime : Math.min(followUpTime, result);
            }
        }

        /*int finalResult = result;
        // 取一个最小值
        cache.compute(curr.code + "-" + target, (k, v) -> {
            if (v == null) {
                return finalResult;
            } else {
                return Math.min(finalResult, v);
            }
        });*/

        cache.putIfAbsent(cacheKey, result);
        return result;
    }

    public static void main(String[] args) {
        //int[][] input = {{2,1,1},{2,3,1},{3,4,1}};
        int[][] input = {{14,1,8},{11,2,25},{14,15,37},{3,7,70},{11,7,60},{13,11,87},{15,10,67},{13,10,58},{5,4,56},{9,3,26},{5,11,51},{11,4,92},{7,6,8},{7,10,95},{14,9,0},{4,13,1},{7,9,89},{3,14,24},{11,15,30},{13,2,91},{15,8,60},{1,4,96},{8,2,71},{6,8,38},{14,13,46},{2,12,48},{10,11,92},{8,12,28},{8,7,12},{9,13,82},{8,6,27},{3,2,65},{4,10,62},{11,13,55},{1,2,52},{8,3,98},{7,12,85},{6,12,97},{9,4,90},{2,4,23},{9,11,20},{1,14,61},{8,9,77},{6,5,80},{14,11,33},{9,8,54},{13,1,42},{13,8,13},{10,14,40},{9,7,18},{14,3,50},{14,6,83},{14,8,14},{2,1,86},{9,5,54},{11,5,29},{9,12,43},{9,2,74},{14,4,87},{12,7,98},{7,14,13},{4,12,33},{5,2,60},{15,11,33},{8,4,99},{9,6,98},{4,6,57},{6,11,5},{9,15,37},{1,3,30},{9,10,60},{13,12,73},{13,14,56},{1,11,13},{14,2,8},{4,15,60},{11,3,90},{2,5,86},{11,1,1},{13,4,2},{15,7,91},{15,4,51},{11,6,70},{2,7,51},{11,9,37},{4,2,92},{10,4,4},{7,2,30},{13,9,79},{8,15,41},{11,8,18},{15,2,4},{12,14,88},{12,6,9},{12,9,44},{1,6,87},{15,14,42},{4,9,41},{7,15,90},{4,1,84},{7,11,9},{3,11,75},{5,9,2},{2,11,96},{12,5,89},{6,15,25},{5,13,7},{15,5,32},{13,5,84},{7,5,9},{15,3,14},{12,13,4},{5,3,73},{6,9,85},{6,10,29},{1,8,24},{12,3,85},{4,3,60},{1,13,6},{1,5,58},{2,3,29},{14,5,67},{13,15,70},{5,14,94},{15,1,95},{3,1,17},{10,2,6},{11,10,44},{9,14,62},{4,11,32},{15,13,48},{2,10,77},{3,13,90},{5,7,68},{10,6,78},{3,6,95},{10,12,68},{13,6,73},{10,1,8},{10,7,18},{10,5,64},{5,1,55},{13,7,90},{1,9,67},{3,12,76},{14,10,22},{12,8,83},{4,7,76},{8,13,25},{5,6,57},{13,3,90},{6,2,96},{11,14,61},{12,1,94},{12,15,12},{4,8,88},{4,14,27},{7,4,25},{3,9,57},{2,15,90},{1,12,85},{12,11,44},{5,10,13},{5,12,96},{14,7,24},{14,12,98},{10,9,36},{15,6,17},{8,10,11},{2,13,5},{10,3,78},{6,13,11},{5,15,34},{12,10,12},{9,1,68},{10,13,1},{7,13,86},{1,7,62},{2,14,53},{8,14,75},{2,6,49},{10,15,83},{7,8,88},{6,1,87},{8,1,38},{8,11,73},{3,15,1},{3,8,93},{2,8,26},{4,5,26},{3,4,58},{7,1,55},{7,3,84},{5,8,97},{12,4,42},{6,3,71},{6,7,48},{15,12,3},{1,15,30},{10,8,11},{2,9,49},{6,14,95},{3,10,68},{6,4,14},{11,12,29},{1,10,93},{8,5,55},{12,2,86},{3,5,26},{15,9,12}};
        System.out.println(new NetworkDelayTime().networkDelayTime(input, 5, 3));
    }

}
