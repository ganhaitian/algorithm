package com.ganht.algorithm.leetcode;

import java.nio.channels.Pipe;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 *
 * Note:
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 * @author haitian.gan
 */
public class KClosestPointsToOrigin {

    private static class Point implements Comparable<Point>{

        private int[] c;
        private double distance;

        Point(int[] c){
            this.c = c;
            this.distance = Math.sqrt(Math.pow(Math.abs(c[0]),2) + Math.pow(Math.abs(c[1]), 2));
        }

        @Override
        public int compareTo(Point o) {
            return Double.compare(o.distance, this.distance);
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> pointHeap = new PriorityQueue<>();

        Point p;
        for (int[] point : points) {
            p = new Point(point);
            if (pointHeap.size() >= K) {
                Point maxPoint = pointHeap.peek();
                if (maxPoint != null && p.distance < maxPoint.distance) {
                    pointHeap.poll();
                    pointHeap.add(p);
                }
            } else {
                pointHeap.add(p);
            }
        }

        List<int[]> kThList = pointHeap.stream()
                .map(b -> b.c)
                .collect(Collectors.toList());

        int[][] result = new int[K][2];
        for (int i = 0; i < kThList.size(); i++) {
            result[i] = kThList.get(i);
        }

        return result;
    }

    public static void main(String[] args){
        int[][] input = {{3,3},{5,-1},{-2,4}};
        int[][] result = new KClosestPointsToOrigin().kClosest(input,2);
        System.out.println(result);
    }

}
