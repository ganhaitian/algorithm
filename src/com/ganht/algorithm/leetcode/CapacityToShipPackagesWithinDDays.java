package com.ganht.algorithm.leetcode;

/**
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 * <p>
 * The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the
 * conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 * <p>
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped
 * within D days.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * Output: 15
 * Explanation:
 * A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 * <p>
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * Example 2:
 * <p>
 * Input: weights = [3,2,2,4,1,4], D = 3
 * Output: 6
 * Explanation:
 * A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * Example 3:
 * <p>
 * Input: weights = [1,2,3,1,1], D = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= D <= weights.length <= 50000
 * 1 <= weights[i] <= 500
 *
 * @author haitian.gan
 */
public class CapacityToShipPackagesWithinDDays {

    /**
     * 得用非常巧的思路，比如什么什么查找
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (checkShipWithinDays(weights, i + 1, D - 1, sum)) {
                return sum;
            }
        }

        return sum;
    }

    public boolean checkShipWithinDays(int[] weights, int start, int D, int min) {
        if(start >= weights.length){
            return false;
        }

        if (D <= 0) {
            return false;
        }

        if (weights[start] > min) {
            return false;
        }

        int sum = 0;
        for (int i = start; i < weights.length; i++) {
            sum += weights[i];
            if (sum > min) {
                return checkShipWithinDays(weights, i, D - 1, min);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        new CapacityToShipPackagesWithinDDays().shipWithinDays(input, 5);
    }

}
